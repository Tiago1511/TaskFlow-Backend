package com.event.TaskFlow.persistence.impl;

import com.event.TaskFlow.persistence.converters.PasswordResetRepositoryConverter;
import com.event.TaskFlow.persistence.entities.ResetPasswordEntity;
import com.event.TaskFlow.persistence.repositories.PasswordResetRepository;
import core.password.domain.ResetPassword;
import core.password.ports.PasswordResetService;

import java.util.Objects;

public class PasswordResetServiceImpl implements PasswordResetService {

    private final PasswordResetRepository passwordResetRepository;
    private final PasswordResetRepositoryConverter passwordResetRepositoryConverter;

    public PasswordResetServiceImpl(PasswordResetRepository passwordResetRepository, PasswordResetRepositoryConverter passwordResetRepositoryConverter) {
        this.passwordResetRepository = passwordResetRepository;
        this.passwordResetRepositoryConverter = passwordResetRepositoryConverter;
    }

    @Override
    public ResetPassword saveResetPassword(ResetPassword resetPassword) {
        Objects.requireNonNull(resetPassword, "ResetPassword cannot be null");
        ResetPasswordEntity resetPasswordEntity = passwordResetRepository.save(passwordResetRepositoryConverter.mapToTable(resetPassword));
        return passwordResetRepositoryConverter.mapToEntity(resetPasswordEntity);
    }
}
