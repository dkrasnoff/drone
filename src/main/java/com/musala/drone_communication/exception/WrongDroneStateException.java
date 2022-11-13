package com.musala.drone_communication.exception;

public class WrongDroneStateException extends BaseBadRequestException {
    public WrongDroneStateException(String message) {
        super(message);
    }
}
