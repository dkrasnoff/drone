package com.musala.drone_communication.service.drone;

import com.musala.drone_communication.dao.entity.Drone;
import com.musala.drone_communication.dao.repository.DroneBatteryHistoryRepository;
import com.musala.drone_communication.dao.repository.DroneRepository;
import com.musala.drone_communication.dto.service.DroneDto;
import com.musala.drone_communication.exception.DuplicateDroneException;
import com.musala.drone_communication.mapper.DroneMapper;
import com.musala.drone_communication.service.TransactionalService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
@AllArgsConstructor
public class DroneTransactionalService implements TransactionalService {

    private final DroneRepository droneRepository;
    private final DroneMapper droneMapper;
    private final DroneBatteryHistoryRepository droneBatteryHistoryRepository;

    public Optional<Drone> saveNewDrone(DroneDto droneDto) {
        if (droneRepository.findById(droneDto.getSerialNumber()).isPresent()) {
            throw new DuplicateDroneException(droneDto.getSerialNumber());
        }
        return Optional.of(droneDto)
                .map(droneMapper::mapToEntity)
                .map(droneRepository::save);
    }

    public void updateDroneBattery(Drone drone) {
        droneRepository.setDroneBatteryCapacity(drone.getSerialNumber(), drone.getBatteryCapacity());
        droneBatteryHistoryRepository.save(droneMapper.mapToDroneBatteryHistory(drone));
    }
}
