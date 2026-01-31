package com.event.TaskFlow.api.role.converters;

import com.event.TaskFlow.api.role.rest.RoleRest;
import com.event.TaskFlow.shared.RestConverter;
import com.event.TaskFlow.shared.exception.InvalidDataException;
import core.role.domain.Role;

public class RoleRestConverter implements RestConverter<RoleRest, Role> {

    @Override
    public Role mapToEntity(final RoleRest rest) {
        if (rest == null || rest.getName() == null || rest.getName().isEmpty()) {
            throw new InvalidDataException("Role name is required", "The role name cannot be null or empty.");
        }

        for (Role role : Role.values()) {
            if ((role.getName() != null && role.getName().equalsIgnoreCase(rest.getName()))) {
                return role;
            }
        }

        throw new InvalidDataException("Invalid role", "The role name '" + rest.getName() + "' is not recognized.");

    }

    @Override
    public RoleRest mapToRest(final Role entity) {
        return new RoleRest(entity.getName(), entity.getDescription());
    }
}
