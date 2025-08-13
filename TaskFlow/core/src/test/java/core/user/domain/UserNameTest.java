package core.user.domain;

import core.shared.exception.TaskFlowCoreException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserNameTest {

    @Test
    @DisplayName("Test valid username creation")
    void testValidUserNameCreation() {
        String validUserName = "validUser123";
        UserName userName = new UserName(validUserName);
        assertEquals(validUserName, userName.getUserName());
    }

    @Test
    @DisplayName("Test valid username creation with underscores")
    void testValidUserNameCreationWithUnderscores() {
        String validUserName = "valid_user_123";
        UserName userName = new UserName(validUserName);
        assertEquals(validUserName, userName.getUserName());
    }

    //sem numeros
    @Test
    @DisplayName("Test valid username creation without numbers")
    void testValidUserNameCreationWithoutNumbers() {
        String validUserName = "validUser";
        UserName userName = new UserName(validUserName);
        assertEquals(validUserName, userName.getUserName());
    }
    //sem letras
    @Test
    @DisplayName("Test valid username creation without letters")
    void testValidUserNameCreationWithoutLetters() {
        String validUserName = "1234567890";
        UserName userName = new UserName(validUserName);
        assertEquals(validUserName, userName.getUserName());
    }

    //com numeros e underscores

    @Test
    @DisplayName("Test username creation with null value")
    void testUserNameCreationWithNull() {
        TaskFlowCoreException exception = assertThrows(TaskFlowCoreException.class, () -> {
            new UserName(null);
        });
        assertEquals("User name cannot be empty", exception.getMessage());
    }

    @Test
    @DisplayName("Test username creation with empty string")
    void testUserNameCreationWithEmptyString() {
        TaskFlowCoreException exception = assertThrows(TaskFlowCoreException.class, () -> {
            new UserName("");
        });
        assertEquals("User name cannot be empty", exception.getMessage());
    }

    @Test
    @DisplayName("Test username creation with blank string")
    void testUserNameCreationWithBlankString() {
        TaskFlowCoreException exception = assertThrows(TaskFlowCoreException.class, () -> {
            new UserName(" ");
        });
        assertEquals("User name cannot be empty", exception.getMessage());
    }

    @Test
    @DisplayName("Test username creation with too long name")
    void testUserNameCreationWithTooLongName() {
        TaskFlowCoreException exception = assertThrows(TaskFlowCoreException.class, () -> {
            new UserName("thisusernameiswaytoolongandshouldthrowanexceptionbecauseitexceedsthemaximumlengthallowedLongUserName");
        });
        assertEquals("User name is invalid", exception.getMessage());
    }

    @Test
    @DisplayName("Test username creation with valid length but invalid characters")
    void testUserNameCreationWithValidLengthButInvalidCharacters() {
        TaskFlowCoreException exception = assertThrows(TaskFlowCoreException.class, () -> {
            new UserName("user@name");
        });
        assertEquals("User name is invalid", exception.getMessage());
    }

    @Test
    @DisplayName("Test username equality")
    void testUserNameEquality() {
        UserName userName1 = new UserName("user123");
        UserName userName2 = new UserName("user123");
        UserName userName3 = new UserName("anotherUser");

        assertEquals(userName1, userName2, "User names should be equal");
        assertNotEquals(userName1, userName3, "User names should not be equal");
    }

    @Test
    @DisplayName("Test username hashCode")
    void testUserNameHashCode() {
        UserName userName1 = new UserName("user123");
        UserName userName2 = new UserName("user123");
        UserName userName3 = new UserName("anotherUser");

        assertEquals(userName1.hashCode(), userName2.hashCode(), "Hash codes should be equal for the same username");
        assertNotEquals(userName1.hashCode(), userName3.hashCode(), "Hash codes should not be equal for different usernames");
    }

}