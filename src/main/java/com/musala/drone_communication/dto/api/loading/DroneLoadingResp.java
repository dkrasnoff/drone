package com.musala.drone_communication.dto.api.loading;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class DroneLoadingResp {

    private short availableWeight;
}
