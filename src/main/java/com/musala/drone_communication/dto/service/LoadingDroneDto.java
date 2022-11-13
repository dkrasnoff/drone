package com.musala.drone_communication.dto.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotNull;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LoadingDroneDto {

    @NotNull
    private DroneDto droneDto;
    private Map<MedicationDto, Integer> loadedMedication;

    public Integer getCurrentLoadedWeight() {
        return loadedMedication
                .entrySet()
                .stream()
                .parallel()
                .map(medicationWithAmountDto ->
                        medicationWithAmountDto.getKey().getWeight() * medicationWithAmountDto.getValue())
                .reduce(0, Integer::sum, Integer::sum);
    }
}
