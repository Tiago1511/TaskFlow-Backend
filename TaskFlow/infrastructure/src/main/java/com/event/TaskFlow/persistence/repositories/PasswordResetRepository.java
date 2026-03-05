package com.event.TaskFlow.persistence.repositories;

import com.event.TaskFlow.persistence.entities.PasswordEntity;
import com.event.TaskFlow.persistence.entities.ResetPasswordEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PasswordResetRepository extends JpaRepository<ResetPasswordEntity, Long> {


}
