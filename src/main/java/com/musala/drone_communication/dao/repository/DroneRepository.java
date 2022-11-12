package com.musala.drone_communication.dao.repository;

import com.musala.drone_communication.dao.entity.Drone;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DroneRepository extends JpaRepository<Drone, String> {
}
