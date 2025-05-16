package com.event.TaskFlow.shared.exception;

import core.shared.constants.CoreErrorCode;
import core.shared.exception.TaskFlowCoreException;

/**
 * Utility class responsible for mapping Core layer exceptions ({@link TaskFlowCoreException})
 * to appropriate Controller layer exceptions ({@link TaskFlowException}) based on internal error codes.
 */
public class TaskFlowExceptionMapper {

    /**
     * Maps a {@link TaskFlowCoreException} to a specific controller-level exception
     * by inspecting its internal error code.
     *
     * @param coreException the exception thrown in the Core layer
     * @return a mapped exception appropriate for the Controller layer
     */
    public static TaskFlowException map(TaskFlowCoreException coreException) {
        CoreErrorCode errorCode = CoreErrorCode.fromCode(coreException.error);

        return switch (errorCode) {
            case DATA_ALREADY_EXISTS -> new DataAlreadyExistsException("Data already exists", coreException.getMessage());
            case RESOURCE_NOT_FOUND -> new BadRequestException("Resource not found", coreException.getMessage());
            default -> new TaskFlowException();
        };
    }
}

