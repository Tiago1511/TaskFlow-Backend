package core.user.domain;

import core.role.domain.Role;
import core.shared.exception.TaskFlowCoreException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


class UserTest {

    @Test
    @DisplayName("Test user creation with valid parameters")
    void testUserCreation() {
        Email email = new Email("email@email.com");
        Password password = new Password("StrongPass123!");
        UserName userName = new UserName("username");
        Role role = new Role("role", "description");
        User user = new User(userName, email, password, role);
        assertEquals(userName, user.getUsername());
        assertEquals(email, user.getEmail());
        assertEquals(password, user.getPassword());
    }

    @Test
    @DisplayName("Test user creation with ID")
    void testUserCreationWithId() {
        Long id = 1L;
        Email email = new Email("email@email.com");
        Password password = new Password("StrongPass123!");
        UserName userName = new UserName("username");
        Role role = new Role("role", "description");
        User user =new User(id,userName, email, password, role);
        assertEquals(userName, user.getUsername());
        assertEquals(email, user.getEmail());
        assertEquals(password, user.getPassword());
    }

    @Test
    @DisplayName("Test user ID setter with valid ID")
    void testUserIdSetterWithValidId() {
        Long id = 1L;
        Email email = new Email("email@email.com");
        Password password = new Password("StrongPass123!");
        UserName userName = new UserName("username");
        Role role = new Role("role", "description");
        User user = new User(userName, email, password, role);
        user.setId(id);
        assertEquals(id, user.getId());
    }

    @Test
    @DisplayName("Test user ID setter with invalid ID")
    void testUserIdSetterWithInvalidId() {

        Email email = new Email("email@email.com");
        Password password = new Password("StrongPass123!");
        UserName userName = new UserName("username");
        Role role = new Role("role", "description");
        User user = new User(userName, email, password, role);
        TaskFlowCoreException exception = assertThrows(TaskFlowCoreException.class, () -> {
            user.setId(null);
        });
        assertEquals("ID is invalid", exception.getMessage());
    }


    @Test
    @DisplayName("Test user creation with null username")
    void testUserCreationWithNullUsername() {
        Email email = new Email("email@email.com");
        Password password = new Password("StrongPass123!");
        UserName userName = new UserName("username");
        Role role = new Role("role", "description");
        TaskFlowCoreException exception = assertThrows(TaskFlowCoreException.class, () -> {
            new User(null, email, password, role);
        });
        assertEquals("User name cannot be empty", exception.getMessage());
    }

    @Test
    @DisplayName("Test user creation with null email")
    void testUserCreationWithNullEmail() {
        Password password = new Password("StrongPass123!");
        UserName userName = new UserName("username");
        Role role = new Role("role", "description");
        TaskFlowCoreException exception = assertThrows(TaskFlowCoreException.class, () -> {
            new User(userName, null, password, role);
        });
        assertEquals("Email cannot be empty", exception.getMessage());
    }

    @Test
    @DisplayName("Test user creation with null password")
    void testUserCreationWithNullPassword() {
        Email email = new Email("email@email.com");
        UserName userName = new UserName("username");
        Role role = new Role("role", "description");
        TaskFlowCoreException exception = assertThrows(TaskFlowCoreException.class, () -> {
            new User(userName, email, null, role);
        });
        assertEquals("Password cannot be empty", exception.getMessage());
    }

    @Test
    @DisplayName("Test user creation with null role")
    void testUserCreationWithNullRole() {
        Email email = new Email("email@email.com");
        Password password = new Password("StrongPass123!");
        UserName userName = new UserName("username");
        TaskFlowCoreException exception = assertThrows(TaskFlowCoreException.class, () -> {
            new User(userName, email, password, null);
        });
        assertEquals("Role cannot be empty", exception.getMessage());
    }

    @Test
    @DisplayName("Test user creation with null parameters")
    void testUserCreationWithNullParameters() {
        TaskFlowCoreException exception = assertThrows(TaskFlowCoreException.class, () -> {
            new User(null, null, null, null);
        });
        assertEquals("User name cannot be empty", exception.getMessage());
    }

    @Test
    @DisplayName("Test user creation with invalid ID")
    void testUserCreationWithInvalidId() {
        Email email = new Email("email@email.com");
        Password password = new Password("StrongPass123!");
        UserName userName = new UserName("username");
        Role role = new Role("role", "description");
        TaskFlowCoreException exception = assertThrows(TaskFlowCoreException.class, () -> {
            new User(-1L, userName, email, password, role);
        });
        assertEquals("ID is invalid", exception.getMessage());
    }

    @Test
    @DisplayName("Test user name setter with valid UserName")
    void testUserNameSetterWithValidUserName() {
        Email email = new Email("email@email.com");
        Password password = new Password("StrongPass123!");
        UserName userName = new UserName("username");
        Role role = new Role("role", "description");
        User user = new User(userName, email, password, role);
        UserName newUserName = new UserName("newUsername");
        user.setUsername(newUserName);
        assertEquals(newUserName, user.getUsername());
    }

    @Test
    @DisplayName("Test user name setter with null UserName")
    void testUserNameSetterWithNullUserName() {
        Email email = new Email("email@email.com");
        Password password = new Password("StrongPass123!");
        UserName userName = new UserName("username");
        Role role = new Role("role", "description");
        User user = new User(userName, email, password, role);
        TaskFlowCoreException exception = assertThrows(TaskFlowCoreException.class, () -> {
            user.setUsername(null);
        });
        assertEquals("User name cannot be empty", exception.getMessage());
    }

    @Test
    @DisplayName("Test email setter with valid Email")
    void testEmailSetterWithValidEmail() {
        UserName userName = new UserName("username");
        Password password = new Password("StrongPass123!");
        Email email = new Email("email@email.com");
        Role role = new Role("role", "description");
        User user = new User(userName, email, password, role);
        Email newEmail = new Email("newEmail@email.com");
        user.setEmail(newEmail);
        assertEquals(newEmail, user.getEmail());

    }

    @Test
    @DisplayName("Test email setter with null Email")
    void testEmailSetterWithNullEmail() {
        UserName userName = new UserName("username");
        Password password = new Password("StrongPass123!");
        Email email = new Email("email@email.com");
        Role role = new Role("role", "description");
        User user = new User(userName, email, password, role);
        TaskFlowCoreException exception = assertThrows(TaskFlowCoreException.class, () -> {
            user.setEmail(null);
        });
        assertEquals("Email cannot be empty", exception.getMessage());
    }

    @Test
    @DisplayName("Test password setter with valid Password")
    void testPasswordSetterWithValidPassword() {
        UserName userName = new UserName("username");
        Email email = new Email("email@email.com");
        Password password = new Password("StrongPass123!");
        Role role = new Role("role", "description");
        User user = new User(userName, email, password, role);
        Password newPassword = new Password("NewStrongPass123!");
        user.setPassword(newPassword);
        assertEquals(newPassword, user.getPassword());
    }

    @Test
    @DisplayName("Test password setter with null Password")
    void testPasswordSetterWithNullPassword() {
        UserName userName = new UserName("username");
        Email email = new Email("email@email.com");
        Password password = new Password("StrongPass123!");
        Role role = new Role("role", "description");
        User user = new User(userName, email, password, role);
        TaskFlowCoreException exception = assertThrows(TaskFlowCoreException.class, () -> {
            user.setPassword(null);
        });
        assertEquals("Password cannot be empty", exception.getMessage());
    }

}