package com.event.TaskFlow.api.role.impl;

import com.event.TaskFlow.api.TaskFlowResponse;
import com.event.TaskFlow.api.role.RoleController;
import com.event.TaskFlow.api.role.converters.RoleRestConverter;
import com.event.TaskFlow.api.role.rest.RoleRest;
import com.event.TaskFlow.shared.Constants.CommonConstants;
import com.event.TaskFlow.shared.Constants.RestConstants;
import com.event.TaskFlow.shared.exception.TaskFlowException;
import com.event.TaskFlow.shared.exception.TaskFlowExceptionMapper;
import core.role.domain.Role;
import core.role.useCase.GetRolesUseCase;
import core.shared.exception.TaskFlowCoreException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(RestConstants.APPLICATION_NAME + RestConstants.API_VERSION + RestConstants.RESOURCE_ROLE)
public class RoleControllerImpl implements RoleController {

    private final GetRolesUseCase getRolesUseCase;
    private final RoleRestConverter roleRestConverter;

    public RoleControllerImpl(GetRolesUseCase getRolesUseCase, RoleRestConverter roleRestConverter) {
        this.getRolesUseCase = getRolesUseCase;
        this.roleRestConverter = roleRestConverter;
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public TaskFlowResponse<List<RoleRest>> getAllRoles() throws TaskFlowException {
        try {
            List<Role> roleList = getRolesUseCase.getAllRoles();
            List<RoleRest> roleRests = roleList.stream().map(roleRestConverter::mapToRest).toList();
            return new TaskFlowResponse<>(CommonConstants.SUCCESS, roleRests, String.valueOf(HttpStatus.OK), CommonConstants.OK);
        }catch (TaskFlowCoreException e) {
            throw TaskFlowExceptionMapper.map(e);
        }
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(path = RestConstants.GET_STANDARD_ROLES, produces = MediaType.APPLICATION_JSON_VALUE)
    public TaskFlowResponse<List<RoleRest>>getNonAdminRoles() throws TaskFlowException {
        try {
            List<Role> roleList = getRolesUseCase.getNonAdminRoles();
            List<RoleRest> roleRests = roleList.stream().map(roleRestConverter::mapToRest).toList();
            return new TaskFlowResponse<>(CommonConstants.SUCCESS, roleRests, String.valueOf(HttpStatus.OK), CommonConstants.OK);
        }catch (TaskFlowCoreException e) {
            throw TaskFlowExceptionMapper.map(e);
        }
    }
}
