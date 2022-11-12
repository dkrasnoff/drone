package com.musala.drone_communication.dto.api.battery;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class DroneBatteryCapacityResp {

    private byte capacity;
}
