package com.musala.drone_communication.dao.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "medication")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Medication {

    @Id
    private String code;

    private String name;

    private short weight;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Medication that = (Medication) o;
        return code != null && Objects.equals(code, that.code);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
