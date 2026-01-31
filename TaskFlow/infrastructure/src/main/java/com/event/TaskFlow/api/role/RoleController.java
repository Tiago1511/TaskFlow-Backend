package com.event.TaskFlow.api.role;

import com.event.TaskFlow.api.TaskFlowResponse;
import com.event.TaskFlow.api.role.rest.RoleRest;
import com.event.TaskFlow.shared.exception.TaskFlowException;

public interface RoleController {

    TaskFlowResponse<RoleRest> createRole(RoleRest role) throws TaskFlowException;
}
