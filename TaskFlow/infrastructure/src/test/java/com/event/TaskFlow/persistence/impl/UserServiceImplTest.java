package com.event.TaskFlow.persistence.impl;

import com.event.TaskFlow.persistence.converters.UserRepositoryConverter;
import com.event.TaskFlow.persistence.entities.*;
import com.event.TaskFlow.persistence.repositories.UserRepository;
import com.event.TaskFlow.shared.exception.TaskFlowException;
import core.role.domain.Role;
import core.user.domain.Email;
import core.user.domain.Password;
import core.user.domain.User;
import core.user.domain.UserName;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserRepositoryConverter userRepositoryConverter;

    @InjectMocks
    private UserServiceImpl userService;

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
    @DisplayName("Save user")
    void saveUser() {
        User user = new User(1L, new UserName("User"), new Email("test@test.com"), new Password("Password12@!"), new Role(1L,"Admin","Full access"));
        UserEntity entity = new UserEntity(1L, new UserNameEntity("User"), new PasswordEntity("Password12@!"), new EmailEntity("test@test.com"), new RoleEntity(1L,"Admin","Full access"));

        when(userRepositoryConverter.mapToTable(user)).thenReturn(entity);
        when(userRepository.save(entity)).thenReturn(entity);
        when(userRepositoryConverter.mapToEntity(entity)).thenReturn(user);

        User savedUser = userService.saveUser(user);

        assertNotNull(savedUser);
        assertEquals(user, savedUser);

        verify(userRepositoryConverter, times(1)).mapToTable(user);
        verify(userRepository, times(1)).save(entity);
        verify(userRepositoryConverter, times(1)).mapToEntity(entity);
    }

    @Test
    @DisplayName("Find User")
    void findByEmailOrUserName() {
        User user = new User(1L, new UserName("User"), new Email("test@test.com"), new Password("Password12@!"), new Role(1L,"Admin","Full access"));
        UserEntity entity = new UserEntity(1L, new UserNameEntity("User"), new PasswordEntity("Password12@!"), new EmailEntity("test@test.com"), new RoleEntity(1L,"Admin","Full access"));

        when(userRepository.findByEmail_EmailOrUsername_UserName(user.getEmail().getEmail(), user.getUsername().getUserName()))
                .thenReturn(List.of(entity));
        when(userRepositoryConverter.mapToEntity(entity)).thenReturn(user);

        List<User> userResponse = userService.findByEmailOrUserName(user.getEmail().getEmail(), user.getUsername().getUserName());

        assertFalse(userResponse.isEmpty());

        verify(userRepository, times(1)).findByEmail_EmailOrUsername_UserName("test@test.com", "User");
    }

    @Test
    @DisplayName("Find User - Not Found")
    void findByEmailOrUserName_NotFound() {
        when(userRepository.findByEmail_EmailOrUsername_UserName("test@test.com", "User"))
                .thenReturn(List.of());
        List<User> userResponse = userService.findByEmailOrUserName("test@test.com", "User");
        assertTrue(userResponse.isEmpty());
        verify(userRepository, times(1)).findByEmail_EmailOrUsername_UserName("test@test.com", "User");
    }

    @Test
    @DisplayName("Find User - Empty Parameters")
    void findByEmailOrUserName_EmptyParameters() {
        TaskFlowException exception = assertThrows(
                TaskFlowException.class, () -> userService.findByEmailOrUserName("", "")
        );

        assertEquals("Email and User Names cannot be empty", exception.getMessage());
        verify(userRepository, never()).findByEmail_EmailOrUsername_UserName(anyString(), anyString());
        verify(userRepositoryConverter, never()).mapToEntity(any());
    }

    @Test
    @DisplayName("Find User - Null Parameters")
    void findByEmailOrUserName_NullParameters() {
        TaskFlowException exception = assertThrows(
                TaskFlowException.class, () -> userService.findByEmailOrUserName("", "")
        );

        assertEquals("Email and User Names cannot be empty", exception.getMessage());
        verify(userRepository, never()).findByEmail_EmailOrUsername_UserName(anyString(), anyString());
        verify(userRepositoryConverter, never()).mapToEntity(any());
    }

    @Test
    @DisplayName("Find User - Null Email")
    void findByEmailOrUserName_NullEmail() {
        TaskFlowException exception = assertThrows(
                TaskFlowException.class, () -> userService.findByEmailOrUserName(null, "User")
        );
        assertEquals("Email and User Names cannot be empty", exception.getMessage());
        verify(userRepository, never()).findByEmail_EmailOrUsername_UserName(anyString(), eq("User"));
        verify(userRepositoryConverter, never()).mapToEntity(any());
    }

    @Test
    @DisplayName("Save User - Null User")
    void saveUser_NullUser() {
        Exception exception = assertThrows(NullPointerException.class, () -> userService.saveUser(null));
        assertEquals("User cannot be null", exception.getMessage());
        verify(userRepositoryConverter, never()).mapToTable(any());
        verify(userRepository, never()).save(any());
        verify(userRepositoryConverter, never()).mapToEntity(any());
    }

}