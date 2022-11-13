package com.musala.drone_communication.service;

import com.musala.drone_communication.dto.service.DroneDto;

// todo add approptiate handling for this type of exception
public class DroneIsNotEnoughChargedExeption extends RuntimeException {
    public DroneIsNotEnoughChargedExeption(DroneDto droneDto) {
    }
}
