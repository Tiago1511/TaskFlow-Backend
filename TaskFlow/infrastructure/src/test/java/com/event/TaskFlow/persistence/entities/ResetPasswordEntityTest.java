package com.event.TaskFlow.persistence.entities;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class ResetPasswordEntityTest {

    @Test
    @DisplayName("Create ResetPasswordEntity and verify getters")
    void testResetPasswordEntityCreation() {
        Long id = 1L;
        Long userId = 2L;
        String resetToken = "resetToken123";
        String otp = "otp123";
        String requestIP = "17.172.224.47";
        String requestUserAgent = "Mozilla/5.0";
        Boolean used = false;
        LocalDateTime requestAt = LocalDateTime.now();

        ResetPasswordEntity resetPasswordEntity = new ResetPasswordEntity(id, userId, resetToken, otp, requestIP, requestUserAgent, used, requestAt);

        assertEquals(id, resetPasswordEntity.getId());
        assertEquals(userId, resetPasswordEntity.getUserID());
        assertEquals(resetToken, resetPasswordEntity.getResetToken());
        assertEquals(otp, resetPasswordEntity.getOtp());
        assertEquals(requestIP, resetPasswordEntity.getRequestIP());
        assertEquals(requestUserAgent, resetPasswordEntity.getRequestUserAgent());
        assertNull(resetPasswordEntity.getConfirmIP());
        assertNull(resetPasswordEntity.getConfirmUserAgent());
        assertEquals(used, resetPasswordEntity.getUsed());
        assertEquals(requestAt, resetPasswordEntity.getRequestAt());
    }

    @Test
    @DisplayName("Create ResetPasswordEntity with all parameters and verify getters")
    void testResetPasswordEntityCreationWithAllParameters() {
        Long id = 1L;
        Long userId = 2L;
        String resetToken = "resetToken123";
        String otp = "otp123";
        String requestIP = "17.172.224.47";
        String requestUserAgent = "Mozilla/5.0";
        String confirmIP = "17.172.224.47";
        String confirmUserAgent = "Mozilla/5.0";
        Boolean used = true;
        LocalDateTime requestAt = LocalDateTime.now();
        LocalDateTime confirmAt = LocalDateTime.now().plusMinutes(5);

        ResetPasswordEntity resetPasswordEntity = new ResetPasswordEntity(id, userId, resetToken, otp, requestIP, confirmIP, requestUserAgent, confirmUserAgent, used, requestAt, confirmAt);

        assertEquals(id, resetPasswordEntity.getId());
        assertEquals(userId, resetPasswordEntity.getUserID());
        assertEquals(resetToken, resetPasswordEntity.getResetToken());
        assertEquals(otp, resetPasswordEntity.getOtp());
        assertEquals(requestIP, resetPasswordEntity.getRequestIP());
        assertEquals(requestUserAgent, resetPasswordEntity.getRequestUserAgent());
        assertEquals(confirmIP, resetPasswordEntity.getConfirmIP());
        assertEquals(confirmUserAgent, resetPasswordEntity.getConfirmUserAgent());
        assertEquals(used, resetPasswordEntity.getUsed());
        assertEquals(requestAt, resetPasswordEntity.getRequestAt());
        assertEquals(confirmAt, resetPasswordEntity.getConfirmAt());

    }

    @Test
    @DisplayName("Test ResetPasswordEntity equals and hashCode")
    void testResetPasswordEntityEqualsAndHashCode() {
        Long id = 1L;
        Long userId = 2L;
        String resetToken = "resetToken123";
        String otp = "otp123";
        String requestIP = "17.172.224.47";
        String requestUserAgent = "Mozilla/5.0";
        Boolean used = false;
        LocalDateTime requestAt = LocalDateTime.now();

        ResetPasswordEntity resetPasswordEntity1 = new ResetPasswordEntity(id, userId, resetToken, otp, requestIP, requestUserAgent, used, requestAt);
        ResetPasswordEntity resetPasswordEntity2 = new ResetPasswordEntity(id, userId, resetToken, otp, requestIP, requestUserAgent, used, requestAt);

        assertEquals(resetPasswordEntity1, resetPasswordEntity2);
        assertEquals(resetPasswordEntity1.hashCode(), resetPasswordEntity2.hashCode());

    }

    @Test
    @DisplayName("Test ResetPasswordEntity not equals")
    void testResetPasswordEntityNotEquals() {
        Long id1 = 1L;
        Long id2 = 2L;
        Long userId = 2L;
        String resetToken = "resetToken123";
        String otp = "otp123";
        String requestIP = "17.172.224.47";
        String requestUserAgent = "Mozilla/5.0";
        Boolean used = false;
        LocalDateTime requestAt = LocalDateTime.now();

        ResetPasswordEntity resetPasswordEntity1 = new ResetPasswordEntity(id1, userId, resetToken, otp, requestIP, requestUserAgent, used, requestAt);
        ResetPasswordEntity resetPasswordEntity2 = new ResetPasswordEntity(id2, userId, resetToken, otp, requestIP, requestUserAgent, used, requestAt);

        assertNotEquals(resetPasswordEntity1, resetPasswordEntity2);
        assertNotEquals(resetPasswordEntity1.hashCode(), resetPasswordEntity2.hashCode());
    }
}