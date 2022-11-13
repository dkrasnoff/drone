package com.musala.drone_communication.external;

/**
 * This is a base interface that provides communication with drone operations
 */
public interface DroneCommunicationClient {

    /**
     * Method checks drone battery level
     *
     * @param id drone's id
     * @return actual battery level
     */
    byte checkBattery(String id);
}
