package com.musala.drone_communication.exception;

import com.musala.drone_communication.dto.service.DroneDto;
import lombok.Getter;

@Getter
public class DroneIsNotEnoughChargedException extends BaseBadRequestException {

    private final DroneDto droneDto;

    public DroneIsNotEnoughChargedException(DroneDto droneDto) {
        super(String.format("Drone with id=%s hast not enough charge for loading. Current battery level is %s%%. " +
                        "This drone will be removed from loading list.",
                droneDto.getSerialNumber(), droneDto.getBatteryCapacity()));
        this.droneDto = droneDto;
    }
}
