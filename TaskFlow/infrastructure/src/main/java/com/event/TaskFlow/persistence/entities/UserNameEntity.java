package com.event.TaskFlow.persistence.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class UserNameEntity  {

    @NotBlank(message = "Username cannot be blank")
    @Column(nullable = false, unique = true)
    private String userName;

    protected UserNameEntity() {
    }

    public UserNameEntity(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserNameEntity that = (UserNameEntity) o;
        return Objects.equals(userName, that.userName);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(userName);
    }
}
