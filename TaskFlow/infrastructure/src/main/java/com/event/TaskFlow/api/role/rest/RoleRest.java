package com.event.TaskFlow.api.role.rest;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class RoleRest {

    @NotBlank
    private String name;

    @NotBlank
    private String description;

    public RoleRest() {}

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
