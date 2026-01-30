package com.event.TaskFlow.api.role.rest;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class RoleRest {

    @NotBlank
    private String name;

    @NotBlank
    private String description;

    private Long id;

    public RoleRest() {}

    public RoleRest(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public RoleRest(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Long getId() {
        return id;
    }
}
