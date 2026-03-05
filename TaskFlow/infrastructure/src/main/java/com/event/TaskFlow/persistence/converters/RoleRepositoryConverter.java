package com.event.TaskFlow.persistence.converters;

import com.event.TaskFlow.persistence.entities.RoleEntity;
import com.event.TaskFlow.shared.RepositoryConverter;
import core.role.domain.Role;
import jakarta.validation.constraints.NotNull;


public class RoleRepositoryConverter implements RepositoryConverter<RoleEntity, Role> {

    @Override
    public RoleEntity mapToTable(@NotNull final Role persistenceObject) {
        return new RoleEntity(persistenceObject.getId(), persistenceObject.getName(), persistenceObject.getDescription());
    }

    @Override
    public Role mapToEntity(@NotNull final RoleEntity entityObject) {
        return new Role(entityObject.getId(), entityObject.getName(), entityObject.getDescription());
    }

}
