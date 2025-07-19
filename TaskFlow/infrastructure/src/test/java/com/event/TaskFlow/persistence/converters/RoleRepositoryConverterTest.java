package com.event.TaskFlow.persistence.converters;

import com.event.TaskFlow.persistence.entities.RoleEntity;
import core.role.domain.Role;
import core.shared.exception.TaskFlowCoreException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RoleRepositoryConverterTest {

    private RoleRepositoryConverter converter;

    @BeforeEach
    void setUp() {
         converter= new RoleRepositoryConverter();
    }

    @Test
    @DisplayName("Map Role to RoleEntity")
    void mapToTable() {
        Role role = new Role(1L, "Admin", "Administrator role");

        RoleEntity entity = converter.mapToTable(role);

        assertNotNull(entity);
        assertEquals(1L, entity.getId());
        assertEquals("Admin", entity.getName());
        assertEquals("Administrator role", entity.getDescription());
    }

    @Test
    @DisplayName("Map RoleEntity to Role")
    void mapToEntity() {
        RoleEntity entity = new RoleEntity(1L, "Admin", "Administrator role");

        Role role = converter.mapToEntity(entity);

        assertNotNull(role);
        assertEquals(1L, role.getId());
        assertEquals("Admin", role.getName());
        assertEquals("Administrator role", role.getDescription());
    }


    @Test
    @DisplayName("Map RoleEntity with null name and description to Role")
    void mapToEntityWithNullValues() {
        RoleEntity entity = new RoleEntity(1L, null, null);

        TaskFlowCoreException exception = assertThrows(TaskFlowCoreException.class, () -> converter.mapToEntity(entity));

        Assertions.assertEquals("Name is invalid", exception.getMessage());
    }

    @Test
    @DisplayName("Map RoleEntity with empty name and description to Role")
    void mapToEntityWithEmptyValues() {
        RoleEntity entity = new RoleEntity(1L, "", "");

        TaskFlowCoreException exception = assertThrows(TaskFlowCoreException.class, () -> converter.mapToEntity(entity));

        Assertions.assertEquals("Name is invalid", exception.getMessage());
    }

    @Test
    @DisplayName("Map RoleEntity with null ID to Role")
    void mapToEntityWithNullId() {
        RoleEntity entity = new RoleEntity(null, "Admin", "Administrator role");

        TaskFlowCoreException exception = assertThrows(TaskFlowCoreException.class, () -> converter.mapToEntity(entity));

        Assertions.assertEquals("ID is invalid", exception.getMessage());
    }

    @Test
    @DisplayName("Map RoleEntity with null ID, name and description to Role")
    void mapToEntityWithNullIdAndValues() {
        RoleEntity entity = new RoleEntity(null, null, null);

        TaskFlowCoreException exception = assertThrows(TaskFlowCoreException.class, () -> converter.mapToEntity(entity));

        Assertions.assertEquals("ID is invalid", exception.getMessage());
    }

    @Test
    @DisplayName("Map RoleEntity with empty name and null description to Role")
    void mapToEntityWithEmptyNameAndNullDescription() {
        RoleEntity entity = new RoleEntity(1L, "", null);

        TaskFlowCoreException exception = assertThrows(TaskFlowCoreException.class, () -> converter.mapToEntity(entity));


        Assertions.assertEquals("Name is invalid", exception.getMessage());
    }

    @Test
    @DisplayName("Map RoleEntity with null name and empty description to Role")
    void mapToEntityWithNullNameAndEmptyDescription() {
        RoleEntity entity = new RoleEntity(1L, null, "");

        TaskFlowCoreException exception = assertThrows(TaskFlowCoreException.class, () -> converter.mapToEntity(entity));

        Assertions.assertEquals("Name is invalid", exception.getMessage());
    }

    @Test
    @DisplayName("Map RoleEntity with empty description to Role")
    void mapToEntityWithEmptyDescription() {
        RoleEntity entity = new RoleEntity(1L, "Admin", "");

        TaskFlowCoreException exception = assertThrows(TaskFlowCoreException.class, () -> converter.mapToEntity(entity));

        Assertions.assertEquals("Description is invalid", exception.getMessage());
    }

    @Test
    @DisplayName("Map RoleEntity with null description to Role")
    void mapToEntityWithNullDescription() {
        RoleEntity entity = new RoleEntity(1L, "Admin", null);

        TaskFlowCoreException exception = assertThrows(TaskFlowCoreException.class, () -> converter.mapToEntity(entity));

        Assertions.assertEquals("Description is invalid", exception.getMessage());
    }
}