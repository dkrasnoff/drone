package com.musala.drone_communication.service.drone;

import com.musala.drone_communication.dao.entity.Drone;
import com.musala.drone_communication.dao.repository.DroneRepository;
import com.musala.drone_communication.dto.service.DroneDto;
import com.musala.drone_communication.exception.DroneNotFoundException;
import com.musala.drone_communication.external.DroneCommunicationClient;
import com.musala.drone_communication.mapper.DroneMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * This service provides base operations with registration drones
 */
@Service
@AllArgsConstructor
@Slf4j
public class DroneService {

    private static final int BATCH_SIZE = 10;

    private final DroneTransactionalService droneTransactionalService;
    private final DroneCommunicationClient droneCommunicationClient;
    private final DroneLoadingService droneLoadingService;
    private final DroneRepository droneRepository;
    private final DroneMapper droneMapper;

    /**
     * Method creates drone if it doesn't exist
     *
     * @param droneDto new drone data
     * @return saved drone
     */
    public DroneDto register(DroneDto droneDto) {
        return droneTransactionalService.saveNewDrone(droneDto)
                .map(droneMapper::mapToServiceDto)
                .orElse(null);
    }

    /**
     * Method checks drones battery level and saves it to database
     *
     * @param id drone's identifier
     * @return drone current battery level
     */
    public byte getDroneBatteryLastLevel(String id) {
        final var drone = droneRepository.findById(id)
                .orElseThrow(() -> new DroneNotFoundException("There is no registered drone with id=" + id));
        return updateDroneBattery(drone).getBatteryCapacity();
    }

    /**
     * Method updates battery for all stored drones
     */
    public void updateDronesBattery() {
        Page<Drone> foundDrones;
        int i = 0;
        do {
            foundDrones = droneRepository.findAll(Pageable.ofSize(BATCH_SIZE).withPage(i));
            foundDrones.getContent()
                    .stream()
                    .parallel()
                    .forEach(drone -> {
                                try {
                                    updateDroneBattery(drone);
                                } catch (Exception e) {
                                    log.error("Can not update battery level for drone id=" + drone.getSerialNumber(), e);
                                }
                            }
                    );
            i++;
        } while (!foundDrones.isEmpty());
    }

    private Drone updateDroneBattery(Drone drone) {
        final byte batteryLevel = droneCommunicationClient.checkBattery(drone.getSerialNumber());
        drone.setBatteryCapacity(batteryLevel);
        droneTransactionalService.updateDrone(drone);
        droneLoadingService.updateLoadingDrone(droneMapper.mapToServiceDto(drone));
        return drone;
    }
}
