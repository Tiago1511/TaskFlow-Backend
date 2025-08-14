package com.event.TaskFlow.persistence.entities;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;

import java.io.Serializable;

@Embeddable
public class UserNameEntity implements Serializable {

    @NotBlank(message = "Username cannot be blank")
    private String userName;

    protected UserNameEntity() {
    }

    public UserNameEntity(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

}
