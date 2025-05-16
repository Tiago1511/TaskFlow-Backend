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
import core.role.useCase.CreateRoleUseCase;
import core.shared.exception.TaskFlowCoreException;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(RestConstants.APPLICATION_NAME + RestConstants.API_VERSION + RestConstants.RESOURCE_ROLE)
public class RoleControllerImpl implements RoleController {

    private final CreateRoleUseCase createRoleUseCase;
    private final RoleRestConverter roleRestConverter;

    public RoleControllerImpl(CreateRoleUseCase createRoleUseCase, RoleRestConverter roleRestConverter) {
        this.createRoleUseCase = createRoleUseCase;
        this.roleRestConverter = roleRestConverter;
    }


    @Override
    @ResponseStatus(HttpStatus.OK)
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public TaskFlowResponse<RoleRest> createRole(@Valid @RequestBody final RoleRest role) throws TaskFlowException {

        try {
            Role roleRest = createRoleUseCase.createRole(roleRestConverter.mapToEntity(role));
            return new TaskFlowResponse<>(CommonConstants.SUCCESS, roleRestConverter.mapToRest(roleRest),
                    String.valueOf(HttpStatus.OK), CommonConstants.OK);
        }catch (TaskFlowCoreException e){
            throw TaskFlowExceptionMapper.map(e);
        }

    }
}
