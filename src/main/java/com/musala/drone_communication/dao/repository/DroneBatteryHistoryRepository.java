package com.musala.drone_communication.dao.repository;

import com.musala.drone_communication.dao.entity.DroneBatteryHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface DroneBatteryHistoryRepository extends JpaRepository<DroneBatteryHistory, UUID> {
}
