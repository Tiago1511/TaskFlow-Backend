package core.password.domain;

import core.shared.exception.TaskFlowCoreException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class ResetPasswordTest {

    @Test
    @DisplayName("Test ResetPassword Creation")
    void testResetPasswordCreation() {
        Long id = 1L;
        Long userID = 100L;
        String resetToken = "token123";
        String otp = "otp456";
        String requestIP = "17.172.224.47";
        String requestUserAgent = "Mozilla/5.0";
        ResetPassword resetPassword = new ResetPassword(id, userID, resetToken, otp, requestIP, requestUserAgent);
        assertNotNull(resetPassword);
        assertEquals(id, resetPassword.getId());
        assertEquals(userID, resetPassword.getUserID());
        assertEquals(resetToken, resetPassword.getResetToken());
        assertEquals(otp, resetPassword.getOtp());
        assertEquals(requestIP, resetPassword.getRequestIP());
        assertEquals(requestUserAgent, resetPassword.getRequestUserAgent());
    }

    @Test
    @DisplayName("Test ResetPassword Creation with Invalid ID")
    void testResetPasswordCreationWithInvalidID() {
        Long invalidId = -1L;
        Long userID = 100L;
        String resetToken = "token123";
        String otp = "otp456";
        String requestIP = "17.172.224.47";
        String requestUserAgent = "Mozilla/5.0";
        Exception exception = assertThrows(TaskFlowCoreException.class, () -> new ResetPassword(invalidId, userID, resetToken, otp, requestIP, requestUserAgent));
        String expectedMessage = "ID is invalid";
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    @DisplayName("Test ResetPassword Creation with Empty Token")
    void testResetPasswordCreationWithEmptyToken() {
        Long id = 1L;
        Long userID = 100L;
        String emptyToken = "   ";
        String otp = "otp456";
        String requestIP = "17.172.224.47";
        String requestUserAgent = "Mozilla/5.0";
        Exception exception = assertThrows(TaskFlowCoreException.class, () -> new ResetPassword(id, userID, emptyToken, otp, requestIP, requestUserAgent));

        String expectedMessage = "Token is invalid";
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    @DisplayName("Test ResetPassword Creation with Empty OTP")
    void testResetPasswordCreationWithEmptyOTP() {
        Long id = 1L;
        Long userID = 100L;
        String resetToken = "token123";
        String emptyOtp = "   ";
        String requestIP = "17.172.224.47";
        String requestUserAgent = "Mozilla/5.0";
        Exception exception = assertThrows(TaskFlowCoreException.class, () -> new ResetPassword(id, userID, resetToken, emptyOtp, requestIP, requestUserAgent));

        String expectedMessage = "OTP is invalid";
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    @DisplayName("Test ResetPassword Creation with Invalid Request IP")
    void testResetPasswordCreationWithInvalidRequestIP() {
        Long id = 1L;
        Long userID = 100L;
        String resetToken = "token123";
        String otp = "otp456";
        String requestIP = "";
        String requestUserAgent = "Mozilla/5.0";
        Exception exception = assertThrows(TaskFlowCoreException.class, () -> new ResetPassword(id, userID, resetToken, otp, requestIP, requestUserAgent));
        String expectedMessage = "Request IP is invalid";
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    @DisplayName("Test ResetPassword Creation with Invalid Request User Agent")
    void testResetPasswordCreationWithInvalidRequestUserAgent() {
        Long id = 1L;
        Long userID = 100L;
        String resetToken = "token123";
        String otp = "otp456";
        String requestIP = "17.172.224.47";
        String requestUserAgent = "";
        Exception exception = assertThrows(TaskFlowCoreException.class, () -> new ResetPassword(id, userID, resetToken, otp, requestIP, requestUserAgent));
        String expectedMessage = "Request User Agent is invalid";
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    @DisplayName("Test ResetPassword Creation with Invalid UserID")
    void testResetPasswordCreationWithInvalidUserID() {
        Long id = 1L;
        Long userID = -100L;
        String resetToken = "token123";
        String otp = "otp456";
        String requestIP = "17.172.224.47";
        String requestUserAgent = "Mozilla/5.0";
        Exception exception = assertThrows(TaskFlowCoreException.class, () -> new ResetPassword(id, userID, resetToken, otp, requestIP, requestUserAgent));
        String expectedMessage = "ID is invalid";
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    @DisplayName("Test ResetPassword Creation with Invalid Confirm IP")
    void testResetPasswordCreationWithInvalidConfirmIP() {
        Long id = 1L;
        Long userID = 100L;
        String resetToken = "token123";
        String otp = "otp456";
        String requestIP = "17.172.224.47";
        String confirmIP = "";
        String requestUserAgent = "Mozilla/5.0";
        String confirmUserAgent = "Mozilla/5.0";
        LocalDateTime requestDateTime = LocalDateTime.now();
        Exception exception = assertThrows(TaskFlowCoreException.class, () -> new ResetPassword(id, userID, resetToken, otp, requestIP, confirmIP, requestUserAgent, confirmUserAgent, requestDateTime));
        String expectedMessage = "Request IP is invalid";
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    @DisplayName("Test ResetPassword Creation with Invalid Confirm User Agent")
    void testResetPasswordCreationWithInvalidConfirmUserAgent() {
        Long id = 1L;
        Long userID = 100L;
        String resetToken = "token123";
        String otp = "otp456";
        String requestIP = "17.172.224.47";
        String confirmIP = "17.172.224.47";
        String requestUserAgent = "Mozilla/5.0";
        String confirmUserAgent = "";
        LocalDateTime requestDateTime = LocalDateTime.now();
        Exception exception = assertThrows(TaskFlowCoreException.class, () -> new ResetPassword(id, userID, resetToken, otp, requestIP, confirmIP, requestUserAgent, confirmUserAgent, requestDateTime));
        String expectedMessage = "Confirm User Agent is invalid";
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    @DisplayName("Test ResetPassword Creation with Null Request DateTime")
    void testResetPasswordCreationWithNullRequestDateTime() {
        Long id = 1L;
        Long userID = 100L;
        String resetToken = "token123";
        String otp = "otp456";
        String requestIP = "17.172.224.47";
        String confirmIP = "17.172.224.47";
        String requestUserAgent = "Mozilla/5.0";
        String confirmUserAgent = "Mozilla/5.0";
        Exception exception = assertThrows(TaskFlowCoreException.class, () -> new ResetPassword(id, userID, resetToken, otp, requestIP, confirmIP, requestUserAgent, confirmUserAgent, null));
        String expectedMessage = "Confirm time cannot be before request time";
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    @DisplayName("Test ResetPassword Creation with Valid Confirm Data")
    void testResetPasswordCreationWithValidConfirmData() {
        Long id = 1L;
        Long userID = 100L;
        String resetToken = "token123";
        String otp = "otp456";
        String requestIP = "17.172.224.47";
        String confirmIP = "17.172.224.47";
        String requestUserAgent = "Mozilla/5.0";
        String confirmUserAgent = "Mozilla/5.0";
        LocalDateTime requestDateTime = LocalDateTime.now();
        ResetPassword resetPassword = new ResetPassword(id, userID, resetToken, otp, requestIP, confirmIP, requestUserAgent, confirmUserAgent, requestDateTime);
        assertNotNull(resetPassword);
        assertEquals(id, resetPassword.getId());
        assertEquals(userID, resetPassword.getUserID());
        assertEquals(resetToken, resetPassword.getResetToken());
        assertEquals(otp, resetPassword.getOtp());
        assertEquals(requestIP, resetPassword.getRequestIP());
        assertEquals(confirmIP, resetPassword.getConfirmIP());
        assertEquals(requestUserAgent, resetPassword.getRequestUserAgent());
        assertEquals(confirmUserAgent, resetPassword.getConfirmUserAgent());
        assertEquals(requestDateTime, resetPassword.getRequestAt());
    }

    @Test
    @DisplayName("Test expired ResetPassword Time")
    void testExpiredResetPasswordToken() {
        Long id = 1L;
        Long userID = 100L;
        String resetToken = "token123";
        String otp = "otp456";
        String requestIP = "17.172.224.47";
        String confirmIP = "17.172.224.47";
        String requestUserAgent = "Mozilla/5.0";
        String confirmUserAgent = "Mozilla/5.0";
        LocalDateTime requestDateTime = LocalDateTime.now().minusHours(3);
        Exception exception = assertThrows(TaskFlowCoreException.class, () -> new ResetPassword(id, userID, resetToken, otp, requestIP, confirmIP, requestUserAgent, confirmUserAgent, requestDateTime));
        String expectedMessage = "Confirm time cannot be before request time";
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    @DisplayName("Test IPV4 and IPv6 Address Handling")
    void testIPV4AndIPv6AddressHandling() {
        Long id = 1L;
        Long userID = 100L;
        String resetToken = "token123";
        String otp = "otp456";
        String requestIPV4 = "17.172.224.47";
        String requestIPV6 = "2001:0db8:85a3:0000:0000:8a2e:0370:7334";
        String requestUserAgent = "Mozilla/5.0";
        String confirmUserAgent = "Mozilla/5.0";
        LocalDateTime requestDateTime = LocalDateTime.now();
        ResetPassword resetPasswordV4 = new ResetPassword(id, userID, resetToken, otp, requestIPV4, requestIPV4, requestUserAgent, confirmUserAgent, requestDateTime);
        assertNotNull(resetPasswordV4);
        assertEquals(requestIPV4, resetPasswordV4.getRequestIP());
        ResetPassword resetPasswordV6 = new ResetPassword(id, userID, resetToken, otp, requestIPV6, requestIPV6, requestUserAgent, confirmUserAgent, requestDateTime);
        assertNotNull(resetPasswordV6);
        assertEquals(requestIPV6, resetPasswordV6.getRequestIP());

    }

    @Test
    @DisplayName("Test invalid IPV4 and IPv6 Address Handling")
    void testInvalidIPV4AndIPv6AddressHandling() {
        Long id = 1L;
        Long userID = 100L;
        String resetToken = "token123";
        String otp = "otp456";
        String invalidIPV4 = "999.999.999.999";
        String invalidIPV6 = "2001:0db8:85a3:0000:0000:8a2e:0370:zzzz";
        String requestUserAgent = "Mozilla/5.0";
        String confirmUserAgent = "Mozilla/5.0";
        LocalDateTime requestDateTime = LocalDateTime.now();
        Exception exceptionV4 = assertThrows(TaskFlowCoreException.class, () -> new ResetPassword(id, userID, resetToken, otp, invalidIPV4, invalidIPV4, requestUserAgent, confirmUserAgent, requestDateTime));
        String expectedMessageV4 = "Request IP is invalid";
        assertEquals(expectedMessageV4, exceptionV4.getMessage());
        Exception exceptionV6 = assertThrows(TaskFlowCoreException.class, () -> new ResetPassword(id, userID, resetToken, otp, invalidIPV6, invalidIPV6, requestUserAgent, confirmUserAgent, requestDateTime));
        String expectedMessageV6 = "Request IP is invalid";
        assertEquals(expectedMessageV6, exceptionV6.getMessage());
    }
}