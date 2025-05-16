package com.event.TaskFlow.persistence.repositories;

import com.event.TaskFlow.persistence.entities.RoleEntity;
import core.role.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface RoleRepository extends JpaRepository<RoleEntity, Long> {

    Collection<Role> findRoleEntitiesByName(String name);
}
