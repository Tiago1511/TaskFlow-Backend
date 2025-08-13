package com.event.TaskFlow.api.role.converters;

import com.event.TaskFlow.api.role.rest.RoleRest;
import com.event.TaskFlow.shared.RestConverter;
import core.role.domain.Role;

public class RoleRestConverter implements RestConverter<RoleRest,Role> {

    @Override
    public Role mapToEntity(final RoleRest rest) {

        if (rest.getId() != null) {;
            return new Role(rest.getId(), rest.getName(), rest.getDescription());
        }

        return new Role(rest.getName(), rest.getDescription());
    }

    @Override
    public RoleRest mapToRest(final Role entity) {
        return new RoleRest(entity.getName(), entity.getDescription());
    }
}
