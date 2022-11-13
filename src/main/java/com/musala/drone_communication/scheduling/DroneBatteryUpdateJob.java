package com.musala.drone_communication.scheduling;

import com.musala.drone_communication.service.drone.DroneService;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.util.concurrent.TimeUnit;

/**
 * This class contains scheduled job, which updates all drones batteries level
 */
@Component
@AllArgsConstructor
public class DroneBatteryUpdateJob {

    public DroneService droneService;

    @Scheduled(fixedDelay = 5, timeUnit = TimeUnit.MINUTES)
    public void update() {
        droneService.updateDronesBattery();
    }
}
