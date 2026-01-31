package com.event.TaskFlow.persistence.entities;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RoleEntityTest {

    @Test
    @DisplayName("HashCode should be consistent for equal objects")
    void testHashCodeConsistency() {
        RoleEntity role1 = new RoleEntity(1L, "Admin", "Administrator role");
        RoleEntity role2 = new RoleEntity(1L, "Admin", "Administrator role");
        assertEquals(role1.hashCode(), role2.hashCode());
    }

    @Test
    @DisplayName("HashCode should differ for different objects")
    void testHashCodeDifference() {
        RoleEntity role1 = new RoleEntity(1L, "Admin", "Administrator role");
        RoleEntity role2 = new RoleEntity(2L, "User", "User role");
        assertNotEquals(role1.hashCode(), role2.hashCode());
    }

    @Test
    @DisplayName("Equals should return true for identical objects")
    void testEqualsIdenticalObjects() {
        RoleEntity role1 = new RoleEntity(1L, "Admin", "Administrator role");
        RoleEntity role2 = new RoleEntity(1L, "Admin", "Administrator role");
        assertEquals(role1, role2);
    }

    @Test
    @DisplayName("Equals should return false for different objects")
    void testEqualsDifferentObjects() {
        RoleEntity role1 = new RoleEntity(1L, "Admin", "Administrator role");
        RoleEntity role2 = new RoleEntity(2L, "User", "User role");
        assertNotEquals(role1, role2);
    }

    @Test
    @DisplayName("Equals should return false when compared with null")
    void testEqualsWithNull() {
        RoleEntity role = new RoleEntity(1L, "Admin", "Administrator role");
        assertNotNull(role);
    }

    @Test
    @DisplayName("Equals should return false when compared with different class")
    void testEqualsWithDifferentClass() {
        RoleEntity role = new RoleEntity(1L, "Admin", "Administrator role");
        String differentClassObject = "NotARoleEntity";
        assertNotEquals(role, differentClassObject);
    }

    @Test
    @DisplayName("Getters should return correct values")
    void testGetters() {
        Long id = 1L;
        String name = "Admin";
        String description = "Administrator role";
        RoleEntity role = new RoleEntity(id, name, description);

        assertEquals(id, role.getId());
        assertEquals(name, role.getName());
        assertEquals(description, role.getDescription());
    }
}