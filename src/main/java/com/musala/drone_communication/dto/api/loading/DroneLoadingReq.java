package com.musala.drone_communication.dto.api.loading;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class DroneLoadingReq {

    @NotNull
    private String droneId;

    private List<Medication> medications;

    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @Data
    public static class Medication {
        @Pattern(regexp = "^[A-Za-z_\\-0-9]+$")
        private String code;
        private Integer count;
    }
}
