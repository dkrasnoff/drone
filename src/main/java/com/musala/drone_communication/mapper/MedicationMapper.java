package com.musala.drone_communication.mapper;

import com.musala.drone_communication.dao.entity.Medication;
import com.musala.drone_communication.dto.service.MedicationDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MedicationMapper {
    MedicationDto toServiceDto(Medication medication);
}
