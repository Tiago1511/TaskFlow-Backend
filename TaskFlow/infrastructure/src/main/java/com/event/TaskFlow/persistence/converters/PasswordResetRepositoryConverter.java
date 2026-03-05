package com.event.TaskFlow.persistence.converters;

import com.event.TaskFlow.persistence.entities.ResetPasswordEntity;
import com.event.TaskFlow.shared.RepositoryConverter;
import core.password.domain.ResetPassword;
import jakarta.validation.constraints.NotNull;

public class PasswordResetRepositoryConverter implements RepositoryConverter<ResetPasswordEntity, ResetPassword> {

    @Override
    public ResetPasswordEntity mapToTable(@NotNull final ResetPassword persistenceObject) {
        if (persistenceObject.getUsed())
            return new ResetPasswordEntity(persistenceObject.getId(), persistenceObject.getUserID(), persistenceObject.getResetToken(), persistenceObject.getOtp(), persistenceObject.getRequestIP(), persistenceObject.getConfirmIP(), persistenceObject.getRequestUserAgent(), persistenceObject.getConfirmUserAgent(), persistenceObject.getUsed(), persistenceObject.getRequestAt(), persistenceObject.getConfirmAt());
        else
            return new ResetPasswordEntity(persistenceObject.getId(), persistenceObject.getUserID(), persistenceObject.getResetToken(), persistenceObject.getOtp(), persistenceObject.getRequestIP(), persistenceObject.getRequestUserAgent(), persistenceObject.getUsed(), persistenceObject.getRequestAt());
    }

    @Override
    public ResetPassword mapToEntity(@NotNull final ResetPasswordEntity entityObject) {
        if (entityObject.getUsed())
            return new ResetPassword(entityObject.getId(), entityObject.getUserID(), entityObject.getResetToken(), entityObject.getOtp(), entityObject.getRequestIP(), entityObject.getConfirmIP(), entityObject.getRequestUserAgent(), entityObject.getConfirmUserAgent(), entityObject.getUsed(), entityObject.getRequestAt(), entityObject.getConfirmAt());
        else
            return new ResetPassword(entityObject.getId(), entityObject.getUserID(), entityObject.getResetToken(), entityObject.getOtp(), entityObject.getRequestIP(), entityObject.getRequestUserAgent(), entityObject.getUsed(), entityObject.getRequestAt());

    }
}
