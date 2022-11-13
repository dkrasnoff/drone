package com.musala.drone_communication.exception;

public class DroneOverloadException extends BaseBadRequestException {

    public DroneOverloadException(String message) {
        super(message);
    }
}
