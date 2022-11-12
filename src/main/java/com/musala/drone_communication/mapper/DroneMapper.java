package com.musala.drone_communication.mapper;

import com.musala.drone_communication.dao.entity.Drone;
import com.musala.drone_communication.dto.api.register.DroneRegisteringResp;
import com.musala.drone_communication.dto.api.register.DroneRegistrationReq;
import com.musala.drone_communication.dto.service.DroneDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DroneMapper {

    DroneDto mapToServiceDto(DroneRegistrationReq droneRegistrationReq);

    DroneDto mapToServiceDto(Drone droneRegisteringReq);

    DroneRegisteringResp toRegisteringResp(DroneDto droneDto);

    Drone mapToEntity(DroneDto droneDto);
}
