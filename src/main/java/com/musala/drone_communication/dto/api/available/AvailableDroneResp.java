package com.musala.drone_communication.dto.api.available;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class AvailableDroneResp {

    private List<Drone> drones;

    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @Data
    public static class Drone {
        private String serialNumber;
    }
}
