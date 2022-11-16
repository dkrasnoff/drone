package com.musala.drone_communication.dao.repository;

import com.musala.drone_communication.dao.entity.Drone;
import com.musala.drone_communication.enums.DroneState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

public interface DroneRepository extends JpaRepository<Drone, String> {

    /**
     * Method searches for all drones with given parameters
     *
     * @param droneState      current drone state
     * @param batteryCapacity current drone capacity
     * @return found drones
     */
    List<Drone> findAllByStateIsAndBatteryCapacityGreaterThanEqual(DroneState droneState,
                                                                   byte batteryCapacity);

    @Query(value = "update Drone drone set drone.state = :droneState where drone.serialNumber = :droneId")
    @Transactional
    @Modifying
    void setDroneState(String droneId, DroneState droneState);

    @Query(value = "update Drone drone set drone.batteryCapacity = :batteryCapacity" +
            " where drone.serialNumber = :droneId")
    @Transactional
    @Modifying
    void setDroneBatteryCapacity(String droneId, byte batteryCapacity);
}
