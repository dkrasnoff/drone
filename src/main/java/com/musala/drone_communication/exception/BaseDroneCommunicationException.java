package com.musala.drone_communication.exception;

/**
 * This is a base exception class for all custom exceptions in this microservice
 */
public class BaseDroneCommunicationException extends RuntimeException {

    public BaseDroneCommunicationException(String message) {
        super(message);
    }

    public BaseDroneCommunicationException(String message, Throwable cause) {
        super(message, cause);
    }
}
