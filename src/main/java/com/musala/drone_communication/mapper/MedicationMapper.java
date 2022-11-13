package com.musala.drone_communication.mapper;

import com.musala.drone_communication.dao.entity.Medication;
import com.musala.drone_communication.dto.api.loading.LoadedMedicationResp;
import com.musala.drone_communication.dto.service.MedicationDto;
import org.mapstruct.Mapper;
import java.util.Map;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface MedicationMapper {

    MedicationDto toServiceDto(Medication medication);

    LoadedMedicationResp.Medication toLoadedMedication(MedicationDto medicationDto, Integer count);

    default LoadedMedicationResp toLoadedMedicationResp(Map<MedicationDto, Integer> loadedInDroneMedication) {
        return LoadedMedicationResp.builder()
                .medications(
                        loadedInDroneMedication.entrySet()
                                .stream()
                                .map(entry -> toLoadedMedication(entry.getKey(), entry.getValue()))
                                .collect(Collectors.toList()))
                .build();
    }
}
