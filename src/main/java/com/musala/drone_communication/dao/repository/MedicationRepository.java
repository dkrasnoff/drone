package com.musala.drone_communication.dao.repository;

import com.musala.drone_communication.dao.entity.Medication;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Set;

public interface MedicationRepository extends JpaRepository<Medication, String> {

    /**
     * Method searches for all medications with given codes
     *
     * @param codes codes for search
     * @return found medications
     */
    List<Medication> findAllByCodeIn(Set<String> codes);
}
