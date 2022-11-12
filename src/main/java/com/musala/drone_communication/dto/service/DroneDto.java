package com.musala.drone_communication.dto.service;

import com.musala.drone_communication.enums.DroneModel;
import com.musala.drone_communication.enums.DroneState;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DroneDto {
    private String serialNumber;
    private DroneModel model;
    private short weightLimit;
    private byte batteryCapacity;
    private DroneState state;
}
