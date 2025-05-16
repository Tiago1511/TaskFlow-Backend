package com.event.TaskFlow.persistence.entities;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "ROLE")
public class RoleEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "roleSeq")
    @SequenceGenerator(name = "roleSeq", sequenceName = "role_id_seq", allocationSize = 1)
    private Long id;

    @Column(name = "NAME", nullable = false, unique = true)
    private String name;

    @Column(name = "DESCRIPTION", nullable = false)
    private String description;

    public RoleEntity() {

    }

    public RoleEntity(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }
}
