package com.event.TaskFlow.persistence.repositories;

import com.event.TaskFlow.persistence.entities.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<RoleEntity, Long> {

    Optional<RoleEntity> findRoleEntitiesByName(String name);
}
