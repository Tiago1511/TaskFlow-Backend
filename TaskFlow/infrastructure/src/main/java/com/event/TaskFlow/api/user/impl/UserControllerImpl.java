package com.event.TaskFlow.api.user.impl;

import com.event.TaskFlow.api.TaskFlowResponse;
import com.event.TaskFlow.api.user.UserController;
import com.event.TaskFlow.api.user.converters.UserRestConverter;
import com.event.TaskFlow.api.user.rest.UserRest;
import com.event.TaskFlow.shared.Constants.CommonConstants;
import com.event.TaskFlow.shared.Constants.RestConstants;
import com.event.TaskFlow.shared.exception.TaskFlowException;
import com.event.TaskFlow.shared.exception.TaskFlowExceptionMapper;
import core.shared.exception.TaskFlowCoreException;
import core.user.domain.User;
import core.user.useCase.CreateUserUseCase;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(RestConstants.APPLICATION_NAME + RestConstants.API_VERSION + RestConstants.RESOURCE_USER)
public class UserControllerImpl implements UserController {

    private final CreateUserUseCase createUserUseCase;
    private final UserRestConverter userRestConverter;

    public UserControllerImpl( CreateUserUseCase createUserUseCase, UserRestConverter userRestConverter) {
        this.createUserUseCase = createUserUseCase;
        this.userRestConverter = userRestConverter;
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public TaskFlowResponse<UserRest> createUser(@Valid @RequestBody UserRest userRest) throws TaskFlowException {
        try {
            User user = createUserUseCase.createUser(userRestConverter.mapToEntity(userRest));
            return new TaskFlowResponse<>(CommonConstants.SUCCESS, userRestConverter.mapToRest(user),
                    String.valueOf(HttpStatus.OK), CommonConstants.OK);
        }catch ( TaskFlowCoreException e ) {
            throw TaskFlowExceptionMapper.map(e);
        }
    }
}
