package com.musala.drone_communication.dao.entity;

import com.musala.drone_communication.enums.DroneModel;
import com.musala.drone_communication.enums.DroneState;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "drone")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Drone {

    @Id
    @Column(name = "id")
    private String serialNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "model")
    private DroneModel model;

    @Column(name = "weight_limit")
    private short weightLimit;

    @Column(name = "battery_capacity")
    private byte batteryCapacity;

    @Enumerated(EnumType.STRING)
    @Column(name = "state")
    private DroneState state;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Drone drone = (Drone) o;
        return serialNumber != null && Objects.equals(serialNumber, drone.serialNumber);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
