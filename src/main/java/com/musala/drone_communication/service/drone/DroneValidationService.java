package com.musala.drone_communication.service.drone;

import com.musala.drone_communication.dto.service.LoadingDroneDto;
import com.musala.drone_communication.dto.service.MedicationDto;
import com.musala.drone_communication.enums.DroneState;
import com.musala.drone_communication.exception.DroneIsNotEnoughChargedException;
import com.musala.drone_communication.exception.DroneOverloadException;
import com.musala.drone_communication.exception.WrongDroneStateException;
import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.Set;

@Service
public class DroneValidationService {

    public static final byte MIN_BATTERY_CAPACITY_FOR_LOADING = 25;
    public static final Set<DroneState> AVAILABLE_FOR_LOADING_STATES = Set.of(DroneState.IDLE, DroneState.LOADING);

    /**
     * Method checks prerequisites for loading drone
     *
     * @param loadingDroneDto   loading drone
     * @param medicationsToLoad medications to load
     */
    public void checkBeforeLoading(LoadingDroneDto loadingDroneDto,
                                   Map<MedicationDto, Integer> medicationsToLoad) {

        checkDroneState(loadingDroneDto);
        checkDroneBatteryLevel(loadingDroneDto);
        checkWeightLimit(loadingDroneDto, medicationsToLoad);

    }

    private static void checkDroneState(LoadingDroneDto loadingDroneDto) {
        if (!AVAILABLE_FOR_LOADING_STATES.contains(loadingDroneDto.getDroneDto().getState())) {
            throw new WrongDroneStateException("Drone can not be loading with state = " +
                    loadingDroneDto.getDroneDto().getState());
        }
    }

    private static void checkDroneBatteryLevel(LoadingDroneDto loadingDroneDto) {
        if (loadingDroneDto.getDroneDto().getBatteryCapacity() < MIN_BATTERY_CAPACITY_FOR_LOADING) {
            throw new DroneIsNotEnoughChargedException(loadingDroneDto.getDroneDto());
        }
    }

    private static void checkWeightLimit(LoadingDroneDto loadingDroneDto,
                                         Map<MedicationDto, Integer> medicationsToLoad) {
        final var medicationTotalWeight =
                medicationsToLoad
                        .entrySet()
                        .stream()
                        .parallel()
                        .map(entry -> entry.getKey().getWeight() * entry.getValue())
                        .reduce(0, Integer::sum, Integer::sum);
        final var loadedMedicationWeight = loadingDroneDto.getCurrentLoadedWeight();

        final var weightLimit = loadingDroneDto.getDroneDto().getWeightLimit();
        if (weightLimit < medicationTotalWeight + loadedMedicationWeight) {
            throw new DroneOverloadException(
                    String.format("Total weight of given items is %s and weight of already loaded items is %s " +
                                    "Total weight of all medication will be greater than drone's limit %s",
                            medicationTotalWeight, loadedMedicationWeight, weightLimit));
        }
    }
}
