package com.musala.drone_communication.service;

import com.musala.drone_communication.dao.repository.DroneRepository;
import com.musala.drone_communication.dto.service.DroneDto;
import com.musala.drone_communication.enums.DroneState;
import com.musala.drone_communication.exception.DroneNotFoundException;
import com.musala.drone_communication.exception.DuplicateDroneException;
import com.musala.drone_communication.external.DroneCommunicationClient;
import com.musala.drone_communication.mapper.DroneMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import static com.musala.drone_communication.service.DroneValidationService.MIN_BATTERY_CAPACITY_FOR_LOADING;

/**
 * This service provides base operations with registration drones
 */
@Service
@AllArgsConstructor
public class DroneService {

    private final DroneCommunicationClient droneCommunicationClient;
    private final DroneRepository droneRepository;
    private final DroneMapper droneMapper;

    /**
     * Method creates drone if it doesn't exist
     *
     * @param droneDto new drone data
     * @return saved drone
     */
    @Transactional
    public DroneDto register(DroneDto droneDto) {
        if (droneRepository.findById(droneDto.getSerialNumber()).isPresent()) {
            throw new DuplicateDroneException(droneDto.getSerialNumber());
        }
        return Optional.of(droneDto)
                .map(droneMapper::mapToEntity)
                .map(droneRepository::save)
                .map(droneMapper::mapToServiceDto)
                .orElse(null);
    }

    /**
     * Method searches for all available for loading drones
     *
     * @return list of available for loading drones
     */
    public List<DroneDto> getAvailableDrones() {
        return droneMapper.mapToServiceDtoList(
                droneRepository.findAllByStateIsAndBatteryCapacityGreaterThanEqual(
                        DroneState.IDLE, MIN_BATTERY_CAPACITY_FOR_LOADING));
    }

    /**
     * Method checks drones battery level and saves it to database
     *
     * @param id drone's identifier
     * @return drone current battery level
     */
    public byte checkDroneBattery(String id) {
        final var droneOp = droneRepository.findById(id)
                .orElseThrow(() -> new DroneNotFoundException("There is no registered drone with id=" + id));
        final byte batteryLevel = droneCommunicationClient.checkBattery(id);
        droneOp.setBatteryCapacity(batteryLevel);
        droneRepository.save(droneOp);
        return batteryLevel;
    }
}
