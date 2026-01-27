package com.event.TaskFlow.api.user.rest;

import com.event.TaskFlow.api.role.rest.RoleRest;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserRest implements Serializable {

    private Long id;

    @NotBlank
    private String username;

    private String password;

    @NotBlank
    private String email;

    @NotBlank
    private RoleRest role;

    public UserRest(String username, String password, String email, RoleRest role) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
    }

    public UserRest(Long id, String username, String password, String email, RoleRest role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
    }

    public UserRest(String username, String email, RoleRest role) {
        this.username = username;
        this.email = email;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public RoleRest getRole() {
        return role;
    }
}
