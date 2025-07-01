package com.event.TaskFlow.persistence.impl;

import com.event.TaskFlow.persistence.converters.RoleRepositoryConverter;
import com.event.TaskFlow.persistence.entities.RoleEntity;
import com.event.TaskFlow.persistence.repositories.RoleRepository;
import core.role.domain.Role;
import core.role.ports.RoleRepositoryService;

import java.util.Optional;

public class RoleServiceImpl implements RoleRepositoryService {

    private final RoleRepository roleRepository;

    private final RoleRepositoryConverter roleRepositoryConverter;

    public RoleServiceImpl(RoleRepository roleRepository, RoleRepositoryConverter roleRepositoryConverter) {
        this.roleRepository = roleRepository;
        this.roleRepositoryConverter = roleRepositoryConverter;
    }

    @Override
    public Optional<Role> getRole(String roleName) {
        Optional<RoleEntity> roleEntity =  roleRepository.findRoleEntitiesByName(roleName);
        if (roleEntity.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(roleRepositoryConverter.mapToEntity(roleEntity.get()));
    }

    @Override
    public Role saveRole(Role role) {
        RoleEntity roleEntity = roleRepository.save(roleRepositoryConverter.mapToTable(role));
        return roleRepositoryConverter.mapToEntity(roleEntity);
    }
}
