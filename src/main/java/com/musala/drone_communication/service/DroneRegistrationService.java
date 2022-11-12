package com.musala.drone_communication.service;

import com.musala.drone_communication.dao.repository.DroneRepository;
import com.musala.drone_communication.dto.service.DroneDto;
import com.musala.drone_communication.exception.DuplicateDroneException;
import com.musala.drone_communication.mapper.DroneMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.Optional;

/**
 * This service provides base operations with registration drones
 */
@Service
@AllArgsConstructor
public class DroneRegistrationService {

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
}
