package com.musala.drone_communication.controller;

import com.musala.drone_communication.dto.api.register.DroneRegisteringResp;
import com.musala.drone_communication.dto.api.register.DroneRegistrationReq;
import com.musala.drone_communication.mapper.DroneMapper;
import com.musala.drone_communication.service.DroneRegistrationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/drones")
@AllArgsConstructor
public class DroneController {

    private final DroneRegistrationService droneService;
    private final DroneMapper droneMapper;

    @PostMapping
    public DroneRegisteringResp register(@RequestBody @Valid DroneRegistrationReq newDrone) {
        return Optional.of(newDrone)
                .map(droneMapper::mapToServiceDto)
                .map(droneService::register)
                .map(droneMapper::toRegisteringResp)
                .orElse(null);
    }
}
