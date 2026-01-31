package com.event.TaskFlow.api.role.impl;

import com.event.TaskFlow.api.TaskFlowResponse;
import com.event.TaskFlow.api.role.converters.RoleRestConverter;
import com.event.TaskFlow.api.role.rest.RoleRest;
import com.event.TaskFlow.shared.Constants.CommonConstants;
import core.role.domain.Role;
import core.role.useCase.GetRolesUseCaseImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


class RolesControllerImplTest {
    @Mock
    private RoleRestConverter roleRestConverter;

    @Mock
    private GetRolesUseCaseImpl createRoleUseCase;

    @InjectMocks
    private RoleControllerImpl roleControllerImpl;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    // NOTE: Unit tests

    @Test
    @DisplayName("Get All Role Test")
    void getAllRoles() {
        RoleRest roleRest = new RoleRest("Admin", "All permissions");

        Role role = Role.ADMIN;
        List<Role> rolesList = List.of(role);
        when(roleRestConverter.mapToEntity(roleRest)).thenReturn(role);
        when(roleRestConverter.mapToRest(role)).thenReturn(roleRest);
        when(createRoleUseCase.getAllRoles()).thenReturn(rolesList);


        TaskFlowResponse<List<RoleRest>> response = roleControllerImpl.getAllRoles();

        assertNotNull(response);
        assertEquals(CommonConstants.SUCCESS, response.getStatus());
    }

    @Test
    @DisplayName("Get All Role Test")
    void getNonAdminRoles() {
        RoleRest roleRest = new RoleRest("Product Owner", "Create project and organize tasks");

        Role role = Role.PRODUCT_OWNER;
        List<Role> rolesList = List.of(role);
        when(roleRestConverter.mapToEntity(roleRest)).thenReturn(role);
        when(roleRestConverter.mapToRest(role)).thenReturn(roleRest);
        when(createRoleUseCase.getNonAdminRoles()).thenReturn(rolesList);


        TaskFlowResponse<List<RoleRest>> response = roleControllerImpl.getAllRoles();

        assertNotNull(response);
        assertEquals(CommonConstants.SUCCESS, response.getStatus());
    }

}