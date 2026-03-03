package core.password.useCase;

import core.password.domain.ResetPassword;
import core.password.ports.EncodeDecodeBase64Service;
import core.password.ports.PasswordResetService;
import core.password.ports.SendEmailService;
import core.role.domain.Role;
import core.user.domain.Email;
import core.user.domain.Password;
import core.user.domain.User;
import core.user.domain.UserName;
import core.user.ports.UserRepositoryService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class PassWordResetRequestUseCaseImplTest {

    @Mock
    private UserRepositoryService userRepositoryService;

    @Mock
    private EncodeDecodeBase64Service encoderService;

    @Mock
    private SendEmailService sendEmailService;

    @Mock
    private PasswordResetService passwordResetService;

    @InjectMocks
    private PassWordResetRequestUseCaseImpl passWordResetRequestUseCaseImpl;

    private final Role role = new Role(1L, "User", "Default user role");

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
    @DisplayName("Test passWordResetRequest with valid email")
    void testPassWordResetRequest_ValidEmail() {
        Email email = new Email("email@test.com");
        User user = new User(1L, new UserName("teste"), email, new Password("Password1!"), this.role);
        String requestIP = "17.172.224.47";
        String requestUserAgent = "Mozilla/5.0";
        String encodedOTP = "encodedOTP";

        when(userRepositoryService.findByEmail(email.getEmail())).thenReturn(Optional.of(user));
        when(encoderService.encode(anyString())).thenReturn(encodedOTP);
        doNothing().when(sendEmailService).sendPasswordResetEmail(eq(email.getEmail()), anyString(), anyString());
        when(passwordResetService.saveResetPassword(any(ResetPassword.class))).thenAnswer(invocation -> invocation.getArgument(0));

        passWordResetRequestUseCaseImpl.passWordResetRequest(email, requestIP, requestUserAgent);

        verify(userRepositoryService, times(1)).findByEmail(email.getEmail());
        verify(encoderService, times(1)).encode(anyString());
        verify(passwordResetService, times(1)).saveResetPassword(any(ResetPassword.class));
        verify(sendEmailService, times(1)).sendPasswordResetEmail(eq(email.getEmail()), anyString(), anyString());

    }

    @Test
    @DisplayName("Test OTP")
    void testPassWordResetRequest_OTP() {
        Email email = new Email("email@test.com");
        User user = new User(1L, new UserName("teste"), email, new Password("Password1!"), this.role);
        String requestIP = "17.172.224.47";
        String requestUserAgent = "Mozilla/5.0";
        String encodedOTP = "encodedOTP";

        when(userRepositoryService.findByEmail(email.getEmail())).thenReturn(Optional.of(user));
        when(encoderService.encode(anyString())).thenReturn(encodedOTP);
        doNothing().when(sendEmailService).sendPasswordResetEmail(eq(email.getEmail()), anyString(), anyString());
        when(passwordResetService.saveResetPassword(any(ResetPassword.class))).thenAnswer(invocation -> invocation.getArgument(0));

        passWordResetRequestUseCaseImpl.passWordResetRequest(email, requestIP, requestUserAgent);


        verify(userRepositoryService, times(1)).findByEmail(email.getEmail());
        verify(encoderService, times(1)).encode(anyString());
        verify(passwordResetService, times(1)).saveResetPassword(any(ResetPassword.class));
        verify(sendEmailService, times(1)).sendPasswordResetEmail(eq(email.getEmail()), anyString(), anyString());

        // Catch the OTP that was passed to the encoder and validate format (6 digits)
        ArgumentCaptor<String> encoderOtpCaptor = ArgumentCaptor.forClass(String.class);
        verify(encoderService).encode(encoderOtpCaptor.capture());
        String generatedOtp = encoderOtpCaptor.getValue();
        assertNotNull(generatedOtp);
        assertTrue(generatedOtp.matches("\\d{6}"), "OTP have to be a 6-digit number");

        // Catch the ResetPassword that was saved and verify that the saved otp is the encodedOTP
        ArgumentCaptor<ResetPassword> resetCaptor = ArgumentCaptor.forClass(ResetPassword.class);
        verify(passwordResetService).saveResetPassword(resetCaptor.capture());
        ResetPassword saved = resetCaptor.getValue();
        assertNotNull(saved);
        assertEquals(encodedOTP, saved.getOtp(), "OTP salvo deve ser o encodedOTP");

        // catch the OTP sent in the email and ensure it's the same generated OTP
        ArgumentCaptor<String> emailOtpCaptor = ArgumentCaptor.forClass(String.class);
        verify(sendEmailService).sendPasswordResetEmail(eq(email.getEmail()), anyString(), emailOtpCaptor.capture());
        String emailOtp = emailOtpCaptor.getValue();
        assertEquals(generatedOtp, emailOtp, "Same OTP should be sent in email");

    }
}