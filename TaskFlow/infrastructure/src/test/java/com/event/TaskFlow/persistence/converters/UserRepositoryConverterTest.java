package com.event.TaskFlow.persistence.converters;

import com.event.TaskFlow.persistence.entities.*;
import core.role.domain.Role;
import core.user.domain.Email;
import core.user.domain.Password;
import core.user.domain.User;
import core.user.domain.UserName;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserRepositoryConverterTest {


    private UserRepositoryConverter userRepositoryConverter;


    @BeforeEach
    void setUp() {;
        userRepositoryConverter = new UserRepositoryConverter();
    }

    @Test
    @DisplayName("Converte User to UserEntity")
    void mapToTable() {
        UserEntity expected = new UserEntity(1L, new UserNameEntity("User"), new PasswordEntity("Password123!"), new EmailEntity("test@test.com"), Role.ADMIN);
        User user = new User(1L, new UserName("User"), new Email("test@test.com"), new Password("Password123!"), Role.ADMIN);
        UserEntity userResult = userRepositoryConverter.mapToTable(user);
        assertEquals(expected, userResult);
    }

    @Test
    @DisplayName("Converte UserEntity to User")
    void mapToEntity() {
        User expected = new User(1L, new UserName("User"), new Email("test@test.com"), new Password("Password123!"), Role.ADMIN);
        UserEntity userEntity = new UserEntity(1L, new UserNameEntity("User"), new PasswordEntity("Password123!"), new EmailEntity("test@test.com"), Role.ADMIN);
        User userResult = userRepositoryConverter.mapToEntity(userEntity);
        assertEquals(expected, userResult);
    }
}