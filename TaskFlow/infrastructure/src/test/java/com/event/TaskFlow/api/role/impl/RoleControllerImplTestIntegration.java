package com.event.TaskFlow.api.role.impl;

import com.event.TaskFlow.api.TaskFlowResponse;
import com.event.TaskFlow.api.role.converters.RoleRestConverter;
import com.event.TaskFlow.api.role.rest.RoleRest;
import com.event.TaskFlow.shared.exception.DataAlreadyExistsException;
import core.role.domain.Role;
import core.role.ports.RoleRepositoryService;
import core.role.useCase.CreateRoleUseCase;
import core.role.useCase.CreateRoleUseCaseImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

class RoleControllerImplTestIntegration {

    @Mock
    private RoleRepositoryService roleRepositoryService;

    @Mock
    private RoleRestConverter roleRestConverter;

    private CreateRoleUseCase createRoleUseCase;

    private RoleControllerImpl roleControllerImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        createRoleUseCase = new CreateRoleUseCaseImpl(roleRepositoryService);
        roleControllerImpl = new RoleControllerImpl(createRoleUseCase, roleRestConverter);
    }

    // NOTE: Integration tests can be added here to test the interaction with the repository service.

    @Test
    @DisplayName("Integration Test for Create Role Use Case")
    void integrationTestCreateRole() {
        Role role = new Role("Admin", "Administrator role");
        RoleRest roleRest = new RoleRest("Admin", "Administrator role");
        when(roleRepositoryService.getRole("Admin")).thenReturn(java.util.Optional.empty());
        when(roleRepositoryService.saveRole(role)).thenReturn(new Role(1L, "Admin", "Administrator role"));
        when(roleRestConverter.mapToEntity(roleRest)).thenReturn(role);
        when(roleRestConverter.mapToRest(any(Role.class))).thenReturn(roleRest);

        TaskFlowResponse<RoleRest> response = roleControllerImpl.createRole(roleRest);

        assertNotNull(response);
        assertEquals(roleRest, response.getData());
        verify(roleRepositoryService, times(1)).getRole("Admin");
        verify(roleRepositoryService, times(1)).saveRole(role);
    }

    @Test
    @DisplayName("Integration Test for Create Role Use Case with Existing Role")
    void integrationTestCreateRoleAlreadyExists() {
        Role role = new Role("Admin", "Administrator role");
        RoleRest roleRest = new RoleRest("Admin", "Administrator role");
        when(roleRepositoryService.getRole("Admin")).thenReturn(java.util.Optional.of(role));
        when(roleRestConverter.mapToEntity(roleRest)).thenReturn(role);

        DataAlreadyExistsException exception = assertThrows(DataAlreadyExistsException.class, () -> {
            roleControllerImpl.createRole(roleRest);
        });

        assertEquals("This role already exists", exception.getDetail());
        verify(roleRepositoryService, times(1)).getRole("Admin");
        verify(roleRepositoryService, never()).saveRole(any());
    }

    @Test
    @DisplayName("Integration Test for Create Role Use Case with Exception")
    void integrationTestCreateRoleUseCaseException() {
        RoleRest roleRest = new RoleRest("Admin", "Administrator role");
        Role roleWithoutId = new Role("Admin", "Administrator role");

        when(roleRestConverter.mapToEntity(roleRest)).thenReturn(roleWithoutId);
        when(roleRepositoryService.getRole("Admin")).thenThrow(new RuntimeException("DataBase error"));

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            roleControllerImpl.createRole(roleRest);
        });

        assertEquals("DataBase error", exception.getMessage());
        verify(roleRepositoryService, times(1)).getRole("Admin");
        verify(roleRepositoryService, never()).saveRole(any());
    }

    @Test
    @DisplayName("Integration Test for Create Role Use Case with save failure")
    void integrationTestCreateRoleSaveFailure() {
        RoleRest roleRest = new RoleRest("Admin", "Administrator role");
        Role roleWithoutId = new Role("Admin", "Administrator role");

        when(roleRestConverter.mapToEntity(roleRest)).thenReturn(roleWithoutId);
        when(roleRepositoryService.getRole("Admin")).thenReturn(Optional.empty());
        when(roleRepositoryService.saveRole(roleWithoutId)).thenThrow(new RuntimeException("DataBase error"));

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            roleControllerImpl.createRole(roleRest);
        });

        assertEquals("DataBase error", exception.getMessage());
        verify(roleRepositoryService, times(1)).getRole("Admin");
        verify(roleRepositoryService, times(1)).saveRole(roleWithoutId);
    }
}