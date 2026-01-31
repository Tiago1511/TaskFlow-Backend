package com.event.TaskFlow.persistence.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.Objects;

@Entity
@Table(name = "ROLE")
public class RoleEntity  {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "roleSeq")
    @SequenceGenerator(name = "roleSeq", sequenceName = "role_id_seq", allocationSize = 1)
    private Long id;

    @NotBlank(message = "Role name cannot be blank")
    @Column(name = "NAME", nullable = false, unique = true)
    private String name;

    @NotBlank(message = "Description cannot be blank")
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoleEntity that = (RoleEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description);
    }
}
