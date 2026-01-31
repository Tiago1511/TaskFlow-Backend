package com.event.TaskFlow.api.role;

import com.event.TaskFlow.api.TaskFlowResponse;
import com.event.TaskFlow.api.role.rest.RoleRest;
import com.event.TaskFlow.shared.exception.TaskFlowException;

import java.util.List;

public interface RoleController {

    TaskFlowResponse<List<RoleRest>>getAllRoles() throws TaskFlowException;

    TaskFlowResponse<List<RoleRest>>getNonAdminRoles() throws TaskFlowException;
}
