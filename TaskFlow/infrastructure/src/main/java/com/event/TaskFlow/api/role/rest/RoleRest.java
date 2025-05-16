package com.event.TaskFlow.api.role.rest;

import jakarta.validation.constraints.NotBlank;

import jakarta.validation.constraints.NotNull;


import java.io.Serializable;


public class RoleRest implements Serializable {

    @NotBlank
    private String name;

    @NotBlank
    private String description;

    public RoleRest(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
