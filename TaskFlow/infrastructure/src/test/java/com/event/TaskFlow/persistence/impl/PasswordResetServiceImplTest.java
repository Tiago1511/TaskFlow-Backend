package com.event.TaskFlow.persistence.impl;

import com.event.TaskFlow.persistence.converters.PasswordResetRepositoryConverter;
import com.event.TaskFlow.persistence.entities.ResetPasswordEntity;
import com.event.TaskFlow.persistence.repositories.PasswordResetRepository;
import core.password.domain.ResetPassword;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PasswordResetServiceImplTest {

    @Mock
    private PasswordResetRepository passwordResetRepository;

    @Mock
    private PasswordResetRepositoryConverter passwordResetRepositoryConverter;

    @InjectMocks
    private PasswordResetServiceImpl passwordResetService;

    private AutoCloseable mocks;

    @BeforeEach
    void setUp()  {
        mocks = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() throws Exception {
        mocks.close();
    }

    @Test
    @DisplayName("Save ResetPassword")
    void saveResetPassword() {
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

        when(passwordResetRepository.save(resetPasswordEntity)).thenReturn(resetPasswordEntity);
        when(passwordResetRepositoryConverter.mapToTable(resetPassword)).thenReturn(resetPasswordEntity);
        when(passwordResetRepositoryConverter.mapToEntity(resetPasswordEntity)).thenReturn(resetPassword);

        ResetPassword savedResetPassword = passwordResetService.saveResetPassword(resetPassword);

        assertNotNull(savedResetPassword);
        assertEquals(id, savedResetPassword.getId());
        assertEquals(userId, savedResetPassword.getUserID());
        assertEquals(resetToken, savedResetPassword.getResetToken());
        assertEquals(otp, savedResetPassword.getOtp());
        assertEquals(requestIP, savedResetPassword.getRequestIP());
        assertEquals(requestUserAgent, savedResetPassword.getRequestUserAgent());
        assertEquals(used, savedResetPassword.getUsed());
        assertEquals(requestAt, savedResetPassword.getRequestAt());

        verify(passwordResetRepository, times(1)).save(resetPasswordEntity);
        verify(passwordResetRepositoryConverter, times(1)).mapToEntity(resetPasswordEntity);
        verify(passwordResetRepositoryConverter, times(1)).mapToTable(resetPassword);

    }

    @Test
    @DisplayName("Save ResetPassword with all values")
    void saveResetPasswordWithAllValues() {
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

        when(passwordResetRepository.save(resetPasswordEntity)).thenReturn(resetPasswordEntity);
        when(passwordResetRepositoryConverter.mapToTable(resetPassword)).thenReturn(resetPasswordEntity);
        when(passwordResetRepositoryConverter.mapToEntity(resetPasswordEntity)).thenReturn(resetPassword);

        ResetPassword savedResetPassword = passwordResetService.saveResetPassword(resetPassword);

        assertNotNull(savedResetPassword);
        assertEquals(id, savedResetPassword.getId());
        assertEquals(userId, savedResetPassword.getUserID());
        assertEquals(resetToken, savedResetPassword.getResetToken());
        assertEquals(otp, savedResetPassword.getOtp());
        assertEquals(requestIP, savedResetPassword.getRequestIP());
        assertEquals(requestUserAgent, savedResetPassword.getRequestUserAgent());
        assertEquals(used, savedResetPassword.getUsed());
        assertEquals(requestAt, savedResetPassword.getRequestAt());
        assertEquals(confirmIP, savedResetPassword.getConfirmIP());
        assertEquals(confirmUserAgent, savedResetPassword.getConfirmUserAgent());
        assertEquals(confirmAt, savedResetPassword.getConfirmAt());

        verify(passwordResetRepository, times(1)).save(resetPasswordEntity);
        verify(passwordResetRepositoryConverter, times(1)).mapToEntity(resetPasswordEntity);
        verify(passwordResetRepositoryConverter, times(1)).mapToTable(resetPassword);
    }

    @Test
    @DisplayName("Save ResetPassword - Null ResetPassword")
    void saveResetPasswordNull() {
        assertThrows(NullPointerException.class, () -> passwordResetService.saveResetPassword(null));
        verify(passwordResetRepository, times(0)).save(any(ResetPasswordEntity.class));
        verify(passwordResetRepositoryConverter, times(0)).mapToEntity(any(ResetPasswordEntity.class));
        verify(passwordResetRepositoryConverter, times(0)).mapToTable(any(ResetPassword.class));
    }

}