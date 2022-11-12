package com.musala.drone_communication.exception;

import lombok.Getter;

@Getter
public class DuplicateDroneException extends RuntimeException {

    private final String serialNumber;

    public DuplicateDroneException(String serialNumber) {
        this.serialNumber = serialNumber;
    }
}
