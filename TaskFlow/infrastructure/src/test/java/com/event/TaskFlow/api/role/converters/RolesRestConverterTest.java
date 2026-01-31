package com.event.TaskFlow.api.role.converters;

import com.event.TaskFlow.api.role.rest.RoleRest;
import com.event.TaskFlow.shared.exception.InvalidDataException;
import core.role.domain.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;

class RolesRestConverterTest {

    @InjectMocks
    private RoleRestConverter roleConverter;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("Map to entity")
    void mapToEntity() {
        Role expectedRoles = Role.ADMIN;
        RoleRest roleRest = new RoleRest("Admin", "All permission");

        var role = roleConverter.mapToEntity(roleRest);

        assertNotNull(role);
        assertEquals(expectedRoles, role);
    }

    @Test
    @DisplayName("Map to rest")
    void mapToRest() {
        Role role = Role.ADMIN;
        RoleRest expectedRoleRest = new RoleRest("Admin", "All permissions");

        var roleRest = roleConverter.mapToRest(role);

        assertNotNull(roleRest);
        assertEquals(expectedRoleRest.getDescription(), roleRest.getDescription());
        assertEquals(expectedRoleRest.getName(), roleRest.getName());
    }

    @Test
    @DisplayName("Map to entity with invalid data")
    void mapToEntityWithInvalidData() {
        RoleRest roleRest = new RoleRest("InvalidRole", "No permission");
        InvalidDataException exception = assertThrows(InvalidDataException.class, () -> {
            roleConverter.mapToEntity(roleRest);
        });
        String expectedMessage = "The role name 'InvalidRole' is not recognized.";
        assertEquals(expectedMessage, exception.getDetail());
    }

}