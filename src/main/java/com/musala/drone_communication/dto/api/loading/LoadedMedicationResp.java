package com.musala.drone_communication.dto.api.loading;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class LoadedMedicationResp {

    private List<Medication> medications;

    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @Data
    public static class Medication {

        private String code;

        private String name;
        /**
         * Number of medications with this code
         */
        private Integer count;
    }
}
