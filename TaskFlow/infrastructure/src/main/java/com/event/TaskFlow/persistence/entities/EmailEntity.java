package com.event.TaskFlow.persistence.entities;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.io.Serializable;

@Embeddable
public class EmailEntity implements Serializable {

    @Email(message = "Invalid email format")
    @NotBlank(message = "Email cannot be blank")
    private String email;

    protected EmailEntity() {
    }

    public EmailEntity(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }
}
