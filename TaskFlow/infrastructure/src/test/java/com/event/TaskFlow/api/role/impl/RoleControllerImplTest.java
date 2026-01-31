package com.event.TaskFlow.api.role.impl;

import com.event.TaskFlow.api.TaskFlowResponse;
import com.event.TaskFlow.api.role.converters.RoleRestConverter;
import com.event.TaskFlow.api.role.rest.RoleRest;
import com.event.TaskFlow.shared.Constants.CommonConstants;
import com.event.TaskFlow.shared.exception.DataAlreadyExistsException;
import core.role.domain.Role;
import core.role.useCase.CreateRoleUseCase;
import core.shared.constants.CoreErrorCode;
import core.shared.exception.TaskFlowCoreException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


class RoleControllerImplTest {

    @Mock
    private CreateRoleUseCase createRoleUseCase;
    @Mock
    private RoleRestConverter roleRestConverter;

    @InjectMocks
    private RoleControllerImpl roleControllerImpl;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    // NOTE: Unit tests

    @Test
    @DisplayName("Create Role Test")
    void createRole() {
        RoleRest roleRest = new RoleRest("Admin", "Administrator role");

        Role role = new Role(1L, "Admin", "Administrator role");
        Role roleWithoutId = new Role("Admin", "Administrator role");
        when(roleRestConverter.mapToEntity(roleRest)).thenReturn(roleWithoutId);
        when(roleRestConverter.mapToRest(role)).thenReturn(roleRest);
        when(createRoleUseCase.createRole(roleWithoutId)).thenReturn(role);


        TaskFlowResponse<RoleRest> response = roleControllerImpl.createRole(roleRest);

        assertNotNull(response);
        assertEquals(CommonConstants.SUCCESS, response.getStatus());
        assertEquals(roleRest, response.getData());
    }

    @Test
    @DisplayName("This role already exists")
    void createRoleAlreadyExists() {
        RoleRest roleRest = new RoleRest("Admin", "Administrator role");
        Role roleWithoutId = new Role("Admin", "Administrator role");

        when(roleRestConverter.mapToEntity(roleRest)).thenReturn(roleWithoutId);
        doThrow(new TaskFlowCoreException("This role already exists", CoreErrorCode.DATA_ALREADY_EXISTS.getCode()))
                .when(createRoleUseCase).createRole(roleWithoutId);

        DataAlreadyExistsException exception = assertThrows(DataAlreadyExistsException.class, () -> {
            roleControllerImpl.createRole(roleRest);
        });

        assertEquals("This role already exists", exception.getDetail());
    }

    @Test
    @DisplayName("Create Role Use Case Exception")
    void createRoleUseCaseException() {
        RoleRest roleRest = new RoleRest("Admin", "Administrator role");
        Role roleWithoutId = new Role("Admin", "Administrator role");

        when(roleRestConverter.mapToEntity(roleRest)).thenReturn(roleWithoutId);
        doThrow(new RuntimeException("Use case exception"))
                .when(createRoleUseCase).createRole(roleWithoutId);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            roleControllerImpl.createRole(roleRest);
        });

        assertEquals("Use case exception", exception.getMessage());
    }

}