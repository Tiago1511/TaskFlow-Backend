package com.event.TaskFlow.persistence.repositories;

import com.event.TaskFlow.persistence.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    List<UserEntity> findByEmail_EmailOrUsername_UserName(String email, String userName);
}
