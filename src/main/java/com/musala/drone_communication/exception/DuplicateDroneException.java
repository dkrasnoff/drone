package com.musala.drone_communication.exception;

import lombok.Getter;

public class DuplicateDroneException extends BaseBadRequestException {

    public DuplicateDroneException(String serialNumber) {
        super(String.format("Drone with id %s already exists", serialNumber));
    }
}
