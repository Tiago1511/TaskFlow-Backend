package com.event.TaskFlow.api.role.impl;

import com.event.TaskFlow.api.TaskFlowResponse;
import com.event.TaskFlow.api.role.converters.RoleRestConverter;
import com.event.TaskFlow.api.role.rest.RoleRest;
import core.role.domain.Role;
import core.role.useCase.GetRolesUseCase;
import core.role.useCase.GetRolesUseCaseImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class RolesControllerImplTestIntegration {



    @Mock
    private RoleRestConverter roleRestConverter;

    private GetRolesUseCase getRolesUseCase;

    private RoleControllerImpl roleControllerImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        getRolesUseCase = new GetRolesUseCaseImpl();
        roleControllerImpl = new RoleControllerImpl(getRolesUseCase, roleRestConverter);
    }

    // NOTE: Integration tests can be added here to test the interaction with the repository service.

    @Test
    @DisplayName("Integration Test for Get All Roles Use Case")
    void integrationTestGetAllRoles() {
        int totalRoles = Role.values().length;

        TaskFlowResponse<List<RoleRest>> response = roleControllerImpl.getAllRoles();

        assertNotNull(response);
        assertEquals(totalRoles, response.getData().size());
    }

    @Test
    @DisplayName("Integration Test for Get Non-Admin Roles Use Case")
    void integrationTestGetNonAdminRoles() {
        int expectedNonAdminRoles = Role.values().length - 1;

        TaskFlowResponse<List<RoleRest>> response = roleControllerImpl.getNonAdminRoles();

        assertNotNull(response);
        assertEquals(expectedNonAdminRoles, response.getData().size());
    }

}