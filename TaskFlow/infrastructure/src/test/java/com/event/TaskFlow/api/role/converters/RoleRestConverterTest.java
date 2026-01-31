package com.event.TaskFlow.api.role.converters;

import com.event.TaskFlow.api.role.rest.RoleRest;
import core.role.domain.Role;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RoleRestConverterTest {

    @Test
    @DisplayName("Map to entity")
    void mapToEntity() {
        RoleRestConverter converter = new RoleRestConverter();
        Role expectedRole = new Role("Admin", "All permission");
        RoleRest roleRest = new RoleRest("Admin", "All permission");

        var role = converter.mapToEntity(roleRest);

        assertNotNull(role);
        assertEquals(role, expectedRole);
    }

    @Test
    @DisplayName("Map to rest")
    void mapToRest() {
        RoleRestConverter converter = new RoleRestConverter();
        Role role = new Role(1L, "Admin", "All permission");
        RoleRest expectedRoleRest = new RoleRest(1L,"Admin", "All permission");

        var roleRest = converter.mapToRest(role);

        assertNotNull(roleRest);
        assertEquals(roleRest.getDescription(), expectedRoleRest.getDescription());
        assertEquals(roleRest.getName(), expectedRoleRest.getName());
    }

    @Test
    @DisplayName("Map to entity with ID")
    void mapToEntityWithId() {
        RoleRestConverter converter = new RoleRestConverter();
        Role expectedRole = new Role(1L, "Admin", "All permission");
        RoleRest roleRest = new RoleRest(1L, "Admin", "All permission");

        var role = converter.mapToEntity(roleRest);

        assertNotNull(role);
        assertEquals(role, expectedRole);
    }

    @Test
    @DisplayName("Map to rest with ID")
    void mapToRestWithId() {
        RoleRestConverter converter = new RoleRestConverter();
        Role role = new Role(1L, "Admin", "All permission");
        RoleRest expectedRoleRest = new RoleRest(1L, "Admin", "All permission");

        var roleRest = converter.mapToRest(role);

        assertNotNull(roleRest);
        assertEquals(roleRest.getDescription(), expectedRoleRest.getDescription());
        assertEquals(roleRest.getName(), expectedRoleRest.getName());
    }

    @Test
    @DisplayName("Map to entity with null ID")
    void mapToEntityWithNullId() {
        RoleRestConverter converter = new RoleRestConverter();
        Role expectedRole = new Role("Admin", "All permission");
        RoleRest roleRest = new RoleRest(null, "Admin", "All permission");

        var role = converter.mapToEntity(roleRest);

        assertNotNull(role);
        assertEquals(role, expectedRole);
    }

}