package com.musala.drone_communication.dto.api.register;

import com.musala.drone_communication.enums.DroneModel;
import com.musala.drone_communication.enums.DroneState;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class DroneRegistrationReq {

    @NotEmpty
    @Size(max = 100)
    private String serialNumber;
    @NotNull
    private DroneModel model;
    @Max(value = 500)
    private short weightLimit;
    @Min(0)
    @Max(100)
    private byte batteryCapacity;
    private DroneState state;

}
