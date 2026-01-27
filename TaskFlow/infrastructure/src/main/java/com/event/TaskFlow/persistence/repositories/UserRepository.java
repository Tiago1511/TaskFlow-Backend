package com.event.TaskFlow.persistence.repositories;

import com.event.TaskFlow.persistence.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    List<UserEntity> findByEmail_EmailOrUsername_UserName(String email, String userName);
}
