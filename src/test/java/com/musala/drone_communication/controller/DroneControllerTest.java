package com.musala.drone_communication.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.musala.drone_communication.dto.api.register.DroneRegisteringResp;
import com.musala.drone_communication.dto.api.register.DroneRegistrationReq;
import com.musala.drone_communication.enums.DroneModel;
import com.musala.drone_communication.enums.DroneState;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import java.util.Map;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class DroneControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void checkRegisterNewDroneTest() throws Exception {
        final var registrationRequest = DroneRegistrationReq.builder()
                .serialNumber("Serial Number 1")
                .state(DroneState.IDLE)
                .weightLimit((short) 100)
                .batteryCapacity((byte) 100)
                .model(DroneModel.LIGHTWEIGHT)
                .build();
        final var stringResult = mockMvc.perform(MockMvcRequestBuilders.post("/drones")
                        .content(objectMapper.writeValueAsString(registrationRequest))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();
        final var result = objectMapper.readValue(stringResult, DroneRegisteringResp.class);

        Assertions.assertEquals(createDefaultRegistrationResponse(), result);
    }

    @Test
    public void checkRegisterNewDroneBadRequestTest() throws Exception {
        final var registrationRequest =
                DroneRegistrationReq.builder()
                        .serialNumber("")
                        .weightLimit((short) 600)
                        .batteryCapacity((byte) 101)
                        .build();
        final var stringResult = mockMvc.perform(MockMvcRequestBuilders.post("/drones")
                        .content(objectMapper.writeValueAsString(registrationRequest))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andReturn()
                .getResponse()
                .getContentAsString();
        final var result = objectMapper.readValue(stringResult, Map.class);

        Assertions.assertEquals(
                Map.of("serialNumber", "must not be empty",
                        "model", "must not be null",
                        "batteryCapacity", "must be less than or equal to 100",
                        "weightLimit", "must be less than or equal to 500"),
                result);
    }

    @Test
    public void checkRegisterExistedDroneBadRequestTest() throws Exception {
        final var registrationRequest =
                DroneRegistrationReq.builder()
                        .serialNumber("Serial Number 1")
                        .state(DroneState.IDLE)
                        .weightLimit((short) 100)
                        .batteryCapacity((byte) 100)
                        .model(DroneModel.LIGHTWEIGHT)
                        .build();
        mockMvc.perform(MockMvcRequestBuilders.post("/drones")
                        .content(objectMapper.writeValueAsString(registrationRequest))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        final var stringResult = mockMvc.perform(MockMvcRequestBuilders.post("/drones")
                        .content(objectMapper.writeValueAsString(registrationRequest))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andReturn()
                .getResponse()
                .getContentAsString();

        Assertions.assertEquals(
                "Drone with id Serial Number 1 already exists", stringResult);
    }

    private static DroneRegisteringResp createDefaultRegistrationResponse() {
        final var droneRegisteringReq = new DroneRegisteringResp();
        droneRegisteringReq.setSerialNumber("Serial Number 1");
        droneRegisteringReq.setState(DroneState.IDLE);
        droneRegisteringReq.setWeightLimit((short) 100);
        droneRegisteringReq.setBatteryCapacity((byte) 100);
        droneRegisteringReq.setModel(DroneModel.LIGHTWEIGHT);
        return droneRegisteringReq;
    }
}