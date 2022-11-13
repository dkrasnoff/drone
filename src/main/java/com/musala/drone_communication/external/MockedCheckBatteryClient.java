package com.musala.drone_communication.external;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * This is mock realization for check battery client.
 * We don't call a real external service, because there is no info about it.
 * So for our needs this client generate random number from 0 to 25.
 */
@Component
@Slf4j
public class MockedCheckBatteryClient implements DroneCommunicationClient {

    public byte checkBattery(String id) {
        log.info("Checking drone's battery, id={}", id);
        return (byte) (Math.random() * 100);
    }
}
