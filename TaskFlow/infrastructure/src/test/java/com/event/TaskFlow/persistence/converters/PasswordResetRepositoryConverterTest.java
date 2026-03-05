package com.event.TaskFlow.persistence.converters;

import com.event.TaskFlow.persistence.entities.ResetPasswordEntity;
import core.password.domain.ResetPassword;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class PasswordResetRepositoryConverterTest {

    PasswordResetRepositoryConverter converter = new PasswordResetRepositoryConverter();

    @Test
    @DisplayName("Test mapToTable and mapToEntity")
    void testMapToTableAndMapToEntity() {

        Long id = 1L;
        Long userId = 2L;
        String resetToken = "resetToken123";
        String otp = "otp123";
        String requestIP = "17.172.224.47";
        String requestUserAgent = "Mozilla/5.0";
        Boolean used = false;
        LocalDateTime requestAt = LocalDateTime.now();

        ResetPassword resetPassword = new ResetPassword(id, userId, resetToken, otp, requestIP, requestUserAgent, used, requestAt);
        ResetPasswordEntity resetPasswordEntity = new ResetPasswordEntity(id, userId, resetToken, otp, requestIP, requestUserAgent, used, requestAt);

        ResetPasswordEntity mappedEntity = converter.mapToTable(resetPassword);

        assertEquals(resetPasswordEntity, mappedEntity);

        assertEquals(resetPasswordEntity.getId(), mappedEntity.getId());
        assertEquals(resetPasswordEntity.getUserID(), mappedEntity.getUserID());
        assertEquals(resetPasswordEntity.getResetToken(), mappedEntity.getResetToken());
        assertEquals(resetPasswordEntity.getOtp(), mappedEntity.getOtp());
        assertEquals(resetPasswordEntity.getRequestIP(), mappedEntity.getRequestIP());
        assertEquals(resetPasswordEntity.getRequestUserAgent(), mappedEntity.getRequestUserAgent());
        assertEquals(resetPasswordEntity.getUsed(), mappedEntity.getUsed());
        assertEquals(resetPasswordEntity.getRequestAt(), mappedEntity.getRequestAt());
    }

    @Test
    @DisplayName("Test mapToTable with used true")
    void testMapToTableWithUsedTrue() {
        Long id = 1L;
        Long userId = 2L;
        String resetToken = "resetToken123";
        String otp = "otp123";
        String requestIP = "17.172.224.47";
        String requestUserAgent = "Mozilla/5.0";
        Boolean used = true;
        LocalDateTime requestAt = LocalDateTime.now();
        String confirmIP = "17.172.224.47";
        String confirmUserAgent = "Mozilla/5.0";
        LocalDateTime confirmAt = LocalDateTime.now().plusMinutes(5);

        ResetPassword resetPassword = new ResetPassword(id, userId, resetToken, otp, requestIP, confirmIP, requestUserAgent, confirmUserAgent, used, requestAt, confirmAt);
        ResetPasswordEntity resetPasswordEntity = new ResetPasswordEntity(id, userId, resetToken, otp, requestIP, confirmIP, requestUserAgent, confirmUserAgent, used, requestAt, confirmAt);

        ResetPasswordEntity mappedEntity = converter.mapToTable(resetPassword);

        assertEquals(resetPasswordEntity, mappedEntity);
        assertEquals(resetPasswordEntity.getId(), mappedEntity.getId());
        assertEquals(resetPasswordEntity.getUserID(), mappedEntity.getUserID());
        assertEquals(resetPasswordEntity.getResetToken(), mappedEntity.getResetToken());
        assertEquals(resetPasswordEntity.getOtp(), mappedEntity.getOtp());
        assertEquals(resetPasswordEntity.getRequestIP(), mappedEntity.getRequestIP());
        assertEquals(resetPasswordEntity.getRequestUserAgent(), mappedEntity.getRequestUserAgent());
        assertEquals(resetPasswordEntity.getConfirmIP(), mappedEntity.getConfirmIP());
        assertEquals(resetPasswordEntity.getConfirmUserAgent(), mappedEntity.getConfirmUserAgent());
        assertEquals(resetPasswordEntity.getUsed(), mappedEntity.getUsed());
        assertEquals(resetPasswordEntity.getRequestAt(), mappedEntity.getRequestAt());
        assertEquals(resetPasswordEntity.getConfirmAt(), mappedEntity.getConfirmAt());
    }

    @Test
    @DisplayName("Test mapToEntity with used true")
    void testMapToEntityWithUsedTrue() {
        Long id = 1L;
        Long userId = 2L;
        String resetToken = "resetToken123";
        String otp = "otp123";
        String requestIP = "17.172.224.47";
        String requestUserAgent = "Mozilla/5.0";
        Boolean used = true;
        LocalDateTime requestAt = LocalDateTime.now();
        String confirmIP = "17.172.224.47";
        String confirmUserAgent = "Mozilla/5.0";
        LocalDateTime confirmAt = LocalDateTime.now().plusMinutes(5);

        ResetPasswordEntity resetPasswordEntity = new ResetPasswordEntity(id, userId, resetToken, otp, requestIP, confirmIP, requestUserAgent, confirmUserAgent, used, requestAt, confirmAt);
        ResetPassword resetPassword = new ResetPassword(id, userId, resetToken, otp, requestIP, confirmIP, requestUserAgent, confirmUserAgent, used, requestAt, confirmAt);

        ResetPassword mappedEntity = converter.mapToEntity(resetPasswordEntity);

        assertEquals(resetPassword, mappedEntity);
        assertEquals(resetPassword.getId(), mappedEntity.getId());
        assertEquals(resetPassword.getUserID(), mappedEntity.getUserID());
        assertEquals(resetPassword.getResetToken(), mappedEntity.getResetToken());
        assertEquals(resetPassword.getOtp(), mappedEntity.getOtp());
        assertEquals(resetPassword.getRequestIP(), mappedEntity.getRequestIP());
        assertEquals(resetPassword.getRequestUserAgent(), mappedEntity.getRequestUserAgent());
        assertEquals(resetPassword.getConfirmIP(), mappedEntity.getConfirmIP());
        assertEquals(resetPassword.getConfirmUserAgent(), mappedEntity.getConfirmUserAgent());
        assertEquals(resetPassword.getUsed(), mappedEntity.getUsed());
        assertEquals(resetPassword.getRequestAt(), mappedEntity.getRequestAt());
        assertEquals(resetPassword.getConfirmAt(), mappedEntity.getConfirmAt());
    }

    @Test
    @DisplayName("Test mapToEntity with used false")
    void testMapToEntityWithUsedFalse() {
        Long id = 1L;
        Long userId = 2L;
        String resetToken = "resetToken123";
        String otp = "otp123";
        String requestIP = "17.172.224.47";
        String requestUserAgent = "Mozilla/5.0";
        Boolean used = false;
        LocalDateTime requestAt = LocalDateTime.now();

        ResetPasswordEntity resetPasswordEntity = new ResetPasswordEntity(id, userId, resetToken, otp, requestIP, requestUserAgent, used, requestAt);
        ResetPassword resetPassword = new ResetPassword(id, userId, resetToken, otp, requestIP, requestUserAgent, used, requestAt);

        ResetPassword mappedEntity = converter.mapToEntity(resetPasswordEntity);

        assertEquals(resetPassword, mappedEntity);
        assertEquals(resetPassword.getId(), mappedEntity.getId());
        assertEquals(resetPassword.getUserID(), mappedEntity.getUserID());
        assertEquals(resetPassword.getResetToken(), mappedEntity.getResetToken());
        assertEquals(resetPassword.getOtp(), mappedEntity.getOtp());
        assertEquals(resetPassword.getRequestIP(), mappedEntity.getRequestIP());
        assertEquals(resetPassword.getRequestUserAgent(), mappedEntity.getRequestUserAgent());
        assertNull(mappedEntity.getConfirmIP());
        assertNull(mappedEntity.getConfirmUserAgent());
        assertEquals(resetPassword.getUsed(), mappedEntity.getUsed());
        assertEquals(resetPassword.getRequestAt(), mappedEntity.getRequestAt());

    }

    @Test
    @DisplayName("Test mapToEntity with null input")
    void testMapToEntityWithNullInput() {
        assertThrows(NullPointerException.class, () -> converter.mapToEntity(null));

    }

}