package core.user.useCase;

import core.role.domain.Role;
import core.shared.exception.TaskFlowCoreException;
import core.user.domain.Email;
import core.user.domain.Password;
import core.user.domain.User;
import core.user.domain.UserName;
import core.user.ports.UserRepositoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CreateUserUseCaseGetRolesUseCaseImplTest {

    @Mock
    private UserRepositoryService userRepositoryService;

    @InjectMocks
    private CreateUserUseCaseImpl createUserUseCase;

    private final Role roles = Role.ADMIN;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    // NOTE: Unit tests

    @Test
    @DisplayName("Create user successfully")
    void createUser() {
        User user = new User(1L, new UserName("teste"), new Email("email@test.com"), new Password("Password1!"), this.roles);
        when(userRepositoryService.findByEmailOrUserName("email@test.com", "teste")).thenReturn(java.util.Collections.emptyList());
        when(userRepositoryService.saveUser(user)).thenReturn(user);
        User result = createUserUseCase.createUser(user);
        assertNotNull(result);
        assertEquals(user.getId(), result.getId());
        verify(userRepositoryService, times(1)).findByEmailOrUserName("email@test.com", "teste");
        verify(userRepositoryService, times(1)).saveUser(user);
    }

    @Test
    @DisplayName("User with email or username already exists")
    void userAlreadyExists() {
        User user = new User(1L, new UserName("teste"), new Email("email@test.com"), new Password("Password1!"), this.roles);
        when(userRepositoryService.findByEmailOrUserName("email@test.com", "teste")).thenReturn(java.util.Collections.singletonList(user));
        Exception exception = assertThrows(TaskFlowCoreException.class, () -> {
            createUserUseCase.createUser(user);
        });
        String expectedMessage = "User with this email or username already exists";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
        verify(userRepositoryService, times(1)).findByEmailOrUserName("email@test.com", "teste");
        verify(userRepositoryService, times(0)).saveUser(any());
    }

    @Test
    @DisplayName("Create user with invalid password")
    void createUserWithInvalidPassword() {

        Exception exception = assertThrows(TaskFlowCoreException.class, () -> {
            createUserUseCase.createUser(new User(1L, new UserName("teste"), new Email("email@test.com"), new Password("Passw!"), this.roles));
        });
        String expectedMessage = "Password must be between 8 and 100 characters long, contain at least one uppercase letter, one lowercase letter, one digit, and one special character";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
        verify(userRepositoryService, times(0)).findByEmailOrUserName("email@test.com", "teste");
        verify(userRepositoryService, times(0)).saveUser(any());
    }

    @Test
    @DisplayName("Create user with empty username")
    void createUserWithEmptyUsername() {
        Exception exception = assertThrows(TaskFlowCoreException.class, () -> {
            createUserUseCase.createUser(new User(1L, null, new Email("email@test.com"), new Password("Password1!"), this.roles));
        });
        String expectedMessage = "User name cannot be empty";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
        verify(userRepositoryService, times(0)).findByEmailOrUserName("email@test.com", null);
        verify(userRepositoryService, times(0)).saveUser(any());
    }

    @Test
    @DisplayName("Create user with empty email")
    void createUserWithEmptyEmail() {
        Exception exception = assertThrows(TaskFlowCoreException.class, () -> {
            createUserUseCase.createUser(new User(1L, new UserName("teste"), null, new Password("Password1!"), this.roles));
        });
        String expectedMessage = "Email cannot be empty";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
        verify(userRepositoryService, times(0)).findByEmailOrUserName(null, "teste");
        verify(userRepositoryService, times(0)).saveUser(any());
    }

    @Test
    @DisplayName("Create user with invalid email format")
    void createUserWithInvalidEmailFormat() {
        Exception exception = assertThrows(TaskFlowCoreException.class, () -> {
            createUserUseCase.createUser(new User(1L, new UserName("teste"), new Email("invalid-email"), new Password("Password1!"), this.roles));
        });
        String expectedMessage = "Email format is invalid";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
        verify(userRepositoryService, times(0)).findByEmailOrUserName("invalid-email", "teste");
        verify(userRepositoryService, times(0)).saveUser(any());
    }

    @Test
    @DisplayName("Create user with empty password")
    void createUserWithEmptyPassword() {
        Exception exception = assertThrows(Exception.class, () -> {
            createUserUseCase.createUser(new User(1L, new UserName("teste"), new Email("email@test.com"), new Password("Passwo"), this.roles));
        });
        String expectedMessage = "Password must be between 8 and 100 characters long, contain at least one uppercase letter, one lowercase letter, one digit, and one special character";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
        verify(userRepositoryService, times(0)).findByEmailOrUserName("email@test.com", "teste");
        verify(userRepositoryService, times(0)).saveUser(any());
    }

    @Test
    @DisplayName("Create user with empty role")
    void createUserWithEmptyRole() {
        Exception exception = assertThrows(TaskFlowCoreException.class, () -> {
            createUserUseCase.createUser(new User(1L, new UserName("teste"), new Email("email@test.com"), new Password("Password1!"), null));
        });
        String expectedMessage = "Role cannot be empty";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
        verify(userRepositoryService, times(0)).findByEmailOrUserName("email@test.com", "teste");
        verify(userRepositoryService, times(0)).saveUser(any());
    }

    @Test
    @DisplayName("Create user with non-existing role")
    void createUserWithNonExistingRole() {
        Exception exception = assertThrows(TaskFlowCoreException.class, () -> {
            createUserUseCase.createUser(new User(1L, new UserName("teste"), new Email("email@test.com"), new Password("Password1!"), null));
        });
        String expectedMessage = "Role cannot be empty";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
        verify(userRepositoryService, times(0)).findByEmailOrUserName("email@test.com", "teste");
        verify(userRepositoryService, times(0)).saveUser(any());
    }

    @Test
    @DisplayName("Fail to save user")
    void failToSaveUser() {
        User user = new User(1L, new UserName("teste"), new Email("email@test.com"), new Password("Password1!"), this.roles);
        when(userRepositoryService.findByEmailOrUserName("email@test.com", "teste")).thenReturn(java.util.Collections.emptyList());
        when(userRepositoryService.saveUser(user)).thenThrow(new RuntimeException("Database error"));
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            createUserUseCase.createUser(user);
        });
        verify(userRepositoryService, times(1)).findByEmailOrUserName("email@test.com", "teste");
        verify(userRepositoryService, times(1)).saveUser(user);
        assertEquals("Database error", exception.getMessage());
    }

    @Test
    @DisplayName("Compare equals users")
    void compareUsers() {
        User user1 = new User(1L, new UserName("teste"), new Email("email@test.com"), new Password("Password1!"), this.roles);
        User user2 = new User(1L, new UserName("teste"), new Email("email@test.com"), new Password("Password1!"), this.roles);

        assertEquals(user1, user2);
    }

    @Test
    @DisplayName("Compare diferents users")
    void compareDiferentsUsers() {
        User user1 = new User(1L, new UserName("teste"), new Email("email@test.com"), new Password("Password1!"), this.roles);
        User user2 = new User(1L, new UserName("testes"), new Email("email@test.com"), new Password("Password1!"), this.roles);

        assertNotEquals(user1, user2);
    }

    @Test
    @DisplayName("Compare same object user")
    void compareSameObjectUser() {
        User user = new User(1L, new UserName("teste"), new Email("email@test.com"), new Password("Password1!"), this.roles);
        assertEquals(user, user);
    }

    @Test
    @DisplayName("Compare diferents class object")
    void compareDiferentsClassObject() {
        User user = new User(1L, new UserName("teste"), new Email("email@test.com"), new Password("Password1!"), this.roles);

        assertNotEquals("Some String", user);
    }

    @Test
    @DisplayName("Hash code users")
    void hashCodeUsers() {
        User user1 = new User(1L, new UserName("teste"), new Email("email@test.com"), new Password("Password1!"), this.roles);

        assertEquals(Objects.hash(user1.getId(), user1.getUsername(), user1.getEmail(), user1.getPassword(), user1.getRole()), user1.hashCode());
    }
}