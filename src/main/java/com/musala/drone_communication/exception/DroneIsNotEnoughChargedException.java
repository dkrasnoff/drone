package com.musala.drone_communication.exception;

import com.musala.drone_communication.dto.service.DroneDto;
import com.musala.drone_communication.exception.BaseBadRequestException;

// todo add approptiate handling for this type of exception
public class DroneIsNotEnoughChargedException extends BaseBadRequestException {

    public DroneIsNotEnoughChargedException(DroneDto droneDto) {
        super(String.format("Drone with id=%s hast not enough charge for loading. Current battery level is %s%%. " +
                        "This drone will be removed from loading list.",
                droneDto.getSerialNumber(), droneDto.getBatteryCapacity()));
    }
}
