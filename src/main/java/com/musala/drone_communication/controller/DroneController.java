package com.musala.drone_communication.controller;

import com.musala.drone_communication.dto.api.available.AvailableDroneResp;
import com.musala.drone_communication.dto.api.battery.DroneBatteryCapacityResp;
import com.musala.drone_communication.dto.api.loading.DroneLoadingReq;
import com.musala.drone_communication.dto.api.loading.DroneLoadingResp;
import com.musala.drone_communication.dto.api.register.DroneRegisteringResp;
import com.musala.drone_communication.dto.api.register.DroneRegistrationReq;
import com.musala.drone_communication.mapper.DroneMapper;
import com.musala.drone_communication.mapper.MedicationMapper;
import com.musala.drone_communication.service.DroneLoadingService;
import com.musala.drone_communication.service.DroneService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/drones")
@AllArgsConstructor
public class DroneController {

    private final DroneService droneService;
    private final DroneLoadingService droneLoadingService;
    private final DroneMapper droneMapper;
    private final MedicationMapper medicationMapper;

    @PostMapping
    public DroneRegisteringResp register(@RequestBody @Valid DroneRegistrationReq newDrone) {
        return Optional.of(newDrone)
                .map(droneMapper::mapToServiceDto)
                .map(droneService::register)
                .map(droneMapper::toRegisteringResp)
                .orElse(null);
    }

    @GetMapping("/available")
    public AvailableDroneResp getAvailableDrones() {
        return AvailableDroneResp.builder()
                .drones(droneMapper.mapToAvailableResp(droneService.getAvailableDrones()))
                .build();
    }

    @GetMapping("/battery")
    public DroneBatteryCapacityResp getDroneBatteryCapacity(@RequestParam String id) {
        return DroneBatteryCapacityResp.builder()
                .capacity(droneService.checkDroneBattery(id))
                .build();
    }

    @PostMapping("/load")
    public DroneLoadingResp loadDrone(@RequestBody DroneLoadingReq request) {
        final var medicaitonMap = request.getMedications()
                .stream()
                .collect(Collectors.toMap(
                        DroneLoadingReq.Medication::getCode,
                        DroneLoadingReq.Medication::getCount,
                        Integer::sum));
        short availableWeight = droneLoadingService.loadDrone(request.getDroneId(), medicaitonMap);
        return DroneLoadingResp.builder()
                .availableWeight(availableWeight)
                .build();
    }
}
