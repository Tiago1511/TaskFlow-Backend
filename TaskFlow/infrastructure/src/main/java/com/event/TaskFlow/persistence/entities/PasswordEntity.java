package com.event.TaskFlow.persistence.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;

import java.io.Serializable;

@Embeddable
public class PasswordEntity implements Serializable {

    @NotBlank(message = "Password cannot be blank")
    private String password;

    protected PasswordEntity() {
    }

    public PasswordEntity(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
