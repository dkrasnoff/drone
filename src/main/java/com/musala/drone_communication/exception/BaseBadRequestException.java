package com.musala.drone_communication.exception;

import org.springframework.http.HttpStatus;

/**
 * This class is base for all exceptions, which return {@link HttpStatus.BAD_REQUEST}
 */
public class BaseBadRequestException extends BaseDroneCommunicationException {

    public BaseBadRequestException(String message) {
        super(message);
    }
}
