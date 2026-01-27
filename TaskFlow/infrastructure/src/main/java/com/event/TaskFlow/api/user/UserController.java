package com.event.TaskFlow.api.user;

import com.event.TaskFlow.api.TaskFlowResponse;
import com.event.TaskFlow.api.user.rest.UserRest;
import com.event.TaskFlow.shared.exception.TaskFlowException;

public interface UserController {

    TaskFlowResponse<UserRest> createUser(UserRest user) throws TaskFlowException;
}
