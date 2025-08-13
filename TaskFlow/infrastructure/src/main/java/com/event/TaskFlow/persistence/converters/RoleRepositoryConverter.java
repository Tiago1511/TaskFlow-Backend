package com.event.TaskFlow.persistence.converters;

import com.event.TaskFlow.persistence.entities.RoleEntity;
import com.event.TaskFlow.shared.RepositoryConverter;
import core.role.domain.Role;


public class RoleRepositoryConverter implements RepositoryConverter<RoleEntity, Role> {

    @Override
    public RoleEntity mapToTable(final Role persistenceObject) {
        return new RoleEntity(persistenceObject.getId(), persistenceObject.getName(), persistenceObject.getDescription());
    }

    @Override
    public Role mapToEntity(final RoleEntity entityObject) {
        return new Role(entityObject.getId(), entityObject.getName(), entityObject.getDescription());
    }

}
