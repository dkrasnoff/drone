package com.musala.drone_communication.service;

import com.musala.drone_communication.dao.repository.DroneRepository;
import com.musala.drone_communication.dao.repository.MedicationRepository;
import com.musala.drone_communication.dto.service.DroneDto;
import com.musala.drone_communication.dto.service.LoadingDroneDto;
import com.musala.drone_communication.dto.service.MedicationDto;
import com.musala.drone_communication.exception.DroneNotFoundException;
import com.musala.drone_communication.mapper.DroneMapper;
import com.musala.drone_communication.mapper.MedicationMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * This service provides loading operation with drones
 */
@Service
@AllArgsConstructor
@Slf4j
public class DroneLoadingService {

    private static final Map<String, LoadingDroneDto> LOADING_DRONES = new HashMap<>();

    private final DroneValidationService droneValidationService;
    private final MedicationRepository medicationRepository;
    private final DroneRepository droneRepository;
    private final DroneMapper droneMapper;
    private final MedicationMapper medicationMapper;

    public short loadDrone(String droneId, Map<String, Integer> medications) {
        LoadingDroneDto loadingDroneDto;
        synchronized (LOADING_DRONES) {
            if (!LOADING_DRONES.containsKey(droneId)) {
                DroneDto droneDto = droneRepository.findById(droneId)
                        .map(droneMapper::mapToServiceDto)
                        .orElseThrow(() -> new DroneNotFoundException("There is no drone with id=" + droneId));
                loadingDroneDto = new LoadingDroneDto(droneDto, new HashMap<>());
                LOADING_DRONES.put(droneId, loadingDroneDto);
            } else {
                loadingDroneDto = LOADING_DRONES.get(droneId);
            }
        }
        synchronized (loadingDroneDto) {
            final var medicationsToLoad =
                    medicationRepository.findAllByCodeIn(medications.keySet())
                            .stream()
                            .map(medicationMapper::toServiceDto)
                            .collect(Collectors.toMap(
                                    Function.identity(),
                                    medicationDto -> medications.get(medicationDto.getCode())));
            droneValidationService.checkBeforeLoading(loadingDroneDto, medicationsToLoad);
            updateDroneLoadedMedication(medicationsToLoad, loadingDroneDto);
            return (short) (loadingDroneDto.getDroneDto().getWeightLimit() -
                    loadingDroneDto.getCurrentLoadedWeight().shortValue());
        }
    }

    private static void updateDroneLoadedMedication(Map<MedicationDto, Integer> medicationsToLoad,
                                                    LoadingDroneDto loadingDroneDto) {
        final var loadedMedication = loadingDroneDto.getLoadedMedication();
        medicationsToLoad
                .forEach((key, value) -> {
                    if (loadedMedication.containsKey(key)) {
                        loadedMedication.put(key, value + loadedMedication.get(key));
                    } else {
                        loadedMedication.put(key, value);
                    }
                });
    }
}
