package com.musala.drone_communication.dto.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MedicationDto {
    private String code;
    private String name;
    private short weight;
}
