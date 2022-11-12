package com.musala.drone_communication.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum DroneModel {
    @JsonProperty(value = "Lightweight")
    LIGHTWEIGHT,
    @JsonProperty(value = "Middleweight")
    MIDDLEWEIGHT,
    @JsonProperty(value = "Cruiserweight")
    CRUISERWEIGHT,
    @JsonProperty(value = "Heavyweight")
    HEAVYWEIGHT;
}
