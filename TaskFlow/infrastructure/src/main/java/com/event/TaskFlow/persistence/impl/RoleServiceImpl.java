package com.event.TaskFlow.persistence.impl;

import com.event.TaskFlow.persistence.converters.RoleRepositoryConverter;
import com.event.TaskFlow.persistence.entities.RoleEntity;
import com.event.TaskFlow.persistence.repositories.RoleRepository;
import core.role.domain.Role;
import core.role.ports.RoleRepositoryService;

import java.util.Collection;

public class RoleServiceImpl implements RoleRepositoryService {

    private final RoleRepository roleRepository;

    private final RoleRepositoryConverter roleRepositoryConverter;

    public RoleServiceImpl(RoleRepository roleRepository, RoleRepositoryConverter roleRepositoryConverter) {
        this.roleRepository = roleRepository;
        this.roleRepositoryConverter = roleRepositoryConverter;
    }

    @Override
    public Collection<Role> getRole(String roleName) {
        return roleRepository.findRoleEntitiesByName(roleName);
    }

    @Override
    public Role saveRole(Role role) {
        RoleEntity roleEntity = roleRepository.save(roleRepositoryConverter.mapToTable(role));
        return roleRepositoryConverter.mapToEntity(roleEntity);
    }
}
