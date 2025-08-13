package core.user.domain;

import core.shared.exception.TaskFlowCoreException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmailTest {

    @Test
    @DisplayName("Test valid email creation")
    void testValidEmailCreation() {
        String validEmail = "test@email.com";
        Email email = new Email(validEmail);
        assertEquals(validEmail, email.getEmail());
    }

    @Test
    @DisplayName("Test email creation with null value")
    void testEmailCreationWithNull() {
        TaskFlowCoreException exception = assertThrows(TaskFlowCoreException.class, () -> {
            new Email(null);
        });
        assertEquals("Email cannot be empty", exception.getMessage());
    }

    @Test
    @DisplayName("Test email creation with empty string")
    void testEmailCreationWithEmptyString() {
        TaskFlowCoreException exception = assertThrows(TaskFlowCoreException.class, () -> {
            new Email("");
        });
        assertEquals("Email cannot be empty", exception.getMessage());
    }

    @Test
    @DisplayName("Test email creation with invalid format")
    void testEmailCreationWithInvalidFormat() {
        TaskFlowCoreException exception = assertThrows(TaskFlowCoreException.class, () -> {
            new Email("invalid-email");
        });
        assertEquals("Email format is invalid", exception.getMessage());
    }

    @Test
    @DisplayName("Test email creation with valid format but missing domain")
    void testEmailCreationWithValidFormatButMissingDomain() {
        TaskFlowCoreException exception = assertThrows(TaskFlowCoreException.class, () -> {
            new Email("invalid@domain");
        });
        assertEquals("Email format is invalid", exception.getMessage());
    }

    @Test
    @DisplayName("Test email creation with valid format but missing TLD")
    void testEmailCreationWithValidFormatButMissingTLD() {
        TaskFlowCoreException exception = assertThrows(TaskFlowCoreException.class, () -> {
            new Email("invalid@domain.");
        });
        assertEquals("Email format is invalid", exception.getMessage());
    }

    @Test
    @DisplayName("Test email creation with valid format but missing local part")
    void testEmailCreationWithValidFormatButMissingLocalPart() {
        TaskFlowCoreException exception = assertThrows(TaskFlowCoreException.class, () -> {
            new Email("@domain.com");
        });
        assertEquals("Email format is invalid", exception.getMessage());
    }

    @Test
    @DisplayName("Test email creation with valid format but missing '@' symbol")
    void testEmailCreationWithValidFormatButMissingAtSymbol() {
        TaskFlowCoreException exception = assertThrows(TaskFlowCoreException.class, () -> {
            new Email("invaliddomain.com");
        });
        assertEquals("Email format is invalid", exception.getMessage());
    }

    @Test
    @DisplayName("Test email creation with valid format but invalid characters")
    void testEmailCreationWithValidFormatButInvalidCharacters() {
        TaskFlowCoreException exception = assertThrows(TaskFlowCoreException.class, () -> {
            new Email("invalid@domain,com");
        });
        assertEquals("Email format is invalid", exception.getMessage());
    }

    @Test
    @DisplayName("Test email equality with same value")
    void testEmailEqualityWithSameValue() {
        Email email1 = new Email("same@email.com");
        Email email2 = new Email("same@email.com");
        assertEquals(email1, email2);
    }

    @Test
    @DisplayName("Test email equality with different values")
    void testEmailEqualityWithDifferentValues() {
        Email email1 = new Email("email@email.com");
        Email email2 = new Email("emailDiferent@email.com");
        assertNotEquals(email1, email2);
    }

    @Test
    @DisplayName("Test email hashCode with same value")
    void testEmailHashCodeWithSameValue() {
        Email email1 = new Email("email@email.com");
        Email email2 = new Email("email@email.com");
        assertEquals(email1.hashCode(), email2.hashCode());
    }

    @Test
    @DisplayName("Test email hashCode with different values")
    void testEmailHashCodeWithDifferentValues() {
        Email email1 = new Email("email@email.com");
        Email email2 = new Email("emailDiferent@email.com");
        assertNotEquals(email1.hashCode(), email2.hashCode());
    }
}