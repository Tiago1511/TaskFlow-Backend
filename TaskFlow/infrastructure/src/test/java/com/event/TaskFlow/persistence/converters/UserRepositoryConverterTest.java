package com.event.TaskFlow.persistence.converters;

import com.event.TaskFlow.persistence.entities.*;
import core.role.domain.Role;
import core.shared.exception.TaskFlowCoreException;
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

    private RoleRepositoryConverter roleRepositoryConverter;

    @BeforeEach
    void setUp() {
        roleRepositoryConverter = new RoleRepositoryConverter();
        userRepositoryConverter = new UserRepositoryConverter(roleRepositoryConverter);
    }

    @Test
    @DisplayName("Converte User to UserEntity")
    void mapToTable() {
        UserEntity expected = new UserEntity(1L, new UserNameEntity("User"), new PasswordEntity("Password123!"), new EmailEntity("test@test.com"), new RoleEntity(1L, "Admin", "Full access"));
        User user = new User(1L, new UserName("User"), new Email("test@test.com"), new Password("Password123!"), new Role(1L, "Admin", "Full access"));
         UserEntity userResult = userRepositoryConverter.mapToTable(user);
        assertEquals(expected, userResult);
    }

    @Test
    @DisplayName("Converte UserEntity to User")
    void mapToEntity() {
        User expected = new User(1L, new UserName("User"), new Email("test@test.com"), new Password("Password123!"), new Role(1L, "Admin", "Full access"));
        UserEntity userEntity = new UserEntity(1L, new UserNameEntity("User"), new PasswordEntity("Password123!"), new EmailEntity("test@test.com"), new RoleEntity(1L, "Admin", "Full access"));
        User userResult = userRepositoryConverter.mapToEntity(userEntity);
        assertEquals(expected, userResult);
    }
}