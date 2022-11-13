package com.musala.drone_communication.dao.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;
import java.util.Objects;

@Entity
@Table(name = "medication")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Medication {

    @Id
    @Pattern(regexp = "^[A-Z_0-9]+$")
    private String code;

    @Pattern(regexp = "^[A-Za-z_\\-0-9]+$")
    private String name;

    private short weight;

    /**
     * This field stores the path to the medication photo.
     * It can be path in the local environment or file in S3 storage.
     * NOTE: CRUD operations with {@link Medication} are out of the scope for this application.
     * We just pre-initialize some medication in database in one of the liquibase scripts
     */
    @Column(name = "photo_path")
    private String photoPath;

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
