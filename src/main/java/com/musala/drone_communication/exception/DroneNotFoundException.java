package com.musala.drone_communication.exception;

public class DroneNotFoundException extends BaseBadRequestException {

    public DroneNotFoundException(String message) {
        super(message);
    }
}
