package core.user.domain;

import core.shared.exception.TaskFlowCoreException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PasswordTest {

    @Test
    @DisplayName("Test valid password creation")
    void testValidPasswordCreation() {
        String validPassword = "StrongPass123!";
        Password password = new Password(validPassword);
        assertEquals(validPassword, password.getPassword());
    }

    @Test
    @DisplayName("Test password creation with null value")
    void testPasswordCreationWithNull() {
        TaskFlowCoreException exception = assertThrows(TaskFlowCoreException.class, () -> {
            new Password(null);
        });
        assertEquals("Password cannot be empty", exception.getMessage());
    }

    @Test
    @DisplayName("Test password creation with empty string")
    void testPasswordCreationWithEmptyString() {
        TaskFlowCoreException exception = assertThrows(TaskFlowCoreException.class, () -> {
            new Password("");
        });
        assertEquals("Password cannot be empty", exception.getMessage());
    }

    @Test
    @DisplayName("Test password creation with invalid format")
    void testPasswordCreationWithInvalidFormat() {
        TaskFlowCoreException exception = assertThrows(TaskFlowCoreException.class, () -> {
            new Password("weakpass");
        });
        assertEquals("Password must be between 8 and 100 characters long, contain at least one uppercase letter, one lowercase letter, one digit, and one special character", exception.getMessage());

    }

    @Test
    @DisplayName("Test password creation with short password")
    void testPasswordCreationWithShortPassword() {
        TaskFlowCoreException exception = assertThrows(TaskFlowCoreException.class, () -> {
            new Password("Short1!");
        });
        assertEquals("Password must be between 8 and 100 characters long, contain at least one uppercase letter, one lowercase letter, one digit, and one special character", exception.getMessage());
    }

    @Test
    @DisplayName("Test password creation with long password")
    void testPasswordCreationWithLongPassword() {
        TaskFlowCoreException exception = assertThrows(TaskFlowCoreException.class, () -> {
            new Password("ThisPasswordIsWayTooLongAndExceedsTheMaximumAllowedLengthOfOneHundredCharacters123456789011121314151617181920!");
        });
        assertEquals("Password must be between 8 and 100 characters long, contain at least one uppercase letter, one lowercase letter, one digit, and one special character", exception.getMessage());
    }

    @Test
    @DisplayName("Test password creation with missing uppercase letter")
    void testPasswordCreationWithMissingUppercase() {
        TaskFlowCoreException exception = assertThrows(TaskFlowCoreException.class, () -> {
            new Password("missinguppercase1!");
        });
        assertEquals("Password must be between 8 and 100 characters long, contain at least one uppercase letter, one lowercase letter, one digit, and one special character", exception.getMessage());
    }

    @Test
    @DisplayName("Test password creation with missing lowercase letter")
    void testPasswordCreationWithMissingLowercase() {
        TaskFlowCoreException exception = assertThrows(TaskFlowCoreException.class, () -> {
            new Password("MISSINGLOWERCASE1!");
        });
        assertEquals("Password must be between 8 and 100 characters long, contain at least one uppercase letter, one lowercase letter, one digit, and one special character", exception.getMessage());
    }

    @Test
    @DisplayName("Test password creation with missing digit")
    void testPasswordCreationWithMissingDigit() {
        TaskFlowCoreException exception = assertThrows(TaskFlowCoreException.class, () -> {
            new Password("MissingDigit!");
        });
        assertEquals("Password must be between 8 and 100 characters long, contain at least one uppercase letter, one lowercase letter, one digit, and one special character", exception.getMessage());
    }

    @Test
    @DisplayName("Test password creation with missing special character")
    void testPasswordCreationWithMissingSpecialCharacter() {
        TaskFlowCoreException exception = assertThrows(TaskFlowCoreException.class, () -> {
            new Password("MissingSpecialChar1");
        });
        assertEquals("Password must be between 8 and 100 characters long, contain at least one uppercase letter, one lowercase letter, one digit, and one special character", exception.getMessage());
    }



    @Test
    @DisplayName("Test password is equal to other password with same value")
    void testPasswordEquality() {
        Password password1 = new Password("ValidPass123!");
        Password password2 = new Password("ValidPass123!");
        assertEquals(password1, password2);
    }

    @Test
    @DisplayName("Test password is not equal to other password with different value")
    void testPasswordInequality() {
        Password password1 = new Password("ValidPass123!");
        Password password2 = new Password("DifferentPass456@");
        assertNotEquals(password1, password2);
    }

    @Test
    @DisplayName("Test password hashCode")
    void testPasswordHashCode() {
        Password password1 = new Password("ValidPass123!");
        Password password2 = new Password("ValidPass123!");
        assertEquals(password1.hashCode(), password2.hashCode());

        Password password3 = new Password("DifferentPass456@");
        assertNotEquals(password1.hashCode(), password3.hashCode());
    }
}