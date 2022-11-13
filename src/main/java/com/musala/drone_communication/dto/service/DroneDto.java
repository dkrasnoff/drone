package com.musala.drone_communication.dto.service;

import com.musala.drone_communication.enums.DroneModel;
import com.musala.drone_communication.enums.DroneState;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class DroneDto {
    @EqualsAndHashCode.Include
    private String serialNumber;
    private DroneModel model;
    private short weightLimit;
    private byte batteryCapacity;
    private DroneState state;
}
