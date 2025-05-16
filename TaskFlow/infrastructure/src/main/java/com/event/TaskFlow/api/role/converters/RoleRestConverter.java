package com.event.TaskFlow.api.role.converters;

import com.event.TaskFlow.api.role.rest.RoleRest;
import com.event.TaskFlow.shared.RestConverter;
import core.role.domain.Role;

public class RoleRestConverter implements RestConverter<RoleRest,Role> {

    @Override
    public Role mapToEntity(final RoleRest rest) {

        return new Role(null, rest.getName(), rest.getDescription());
    }

    @Override
    public RoleRest mapToRest(final Role entity) {
        return new RoleRest(entity.getName(), entity.getDescription());
    }
}
