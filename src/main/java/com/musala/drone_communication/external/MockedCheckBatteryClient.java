package com.musala.drone_communication.external;

import org.springframework.stereotype.Component;

/**
 * This is mock realization for check battery client.
 * We don't call a real external service, because there is no info about it.
 * So for our needs this client generate random number from 0 to 25.
 */
@Component
public class MockedCheckBatteryClient {

    public byte checkBattery(String id) {
        return (byte) (Math.random() * 100);
    }
}
