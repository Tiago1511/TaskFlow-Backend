package com.event.TaskFlow.persistence.impl;

import com.event.TaskFlow.persistence.converters.UserRepositoryConverter;
import com.event.TaskFlow.persistence.entities.EmailEntity;
import com.event.TaskFlow.persistence.entities.UserEntity;
import com.event.TaskFlow.persistence.repositories.UserRepository;
import com.event.TaskFlow.shared.exception.TaskFlowException;
import core.user.domain.User;
import core.user.ports.UserRepositoryService;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class UserServiceImpl implements UserRepositoryService {

    private final UserRepository userRepository;

    private final UserRepositoryConverter userRepositoryConverter;

    public UserServiceImpl(UserRepository userRepository, UserRepositoryConverter userRepositoryConverter) {
        this.userRepository = userRepository;
        this.userRepositoryConverter = userRepositoryConverter;
    }

    @Override
    public List<User> findByEmailOrUserName(String email, String userName) {

        if (email == null || userName == null) {
            throw new TaskFlowException("Email and User Names cannot be empty", HttpStatus.BAD_REQUEST);
        } else if (email.isBlank() && userName.isBlank()) {
            throw new TaskFlowException("Email and User Names cannot be empty", HttpStatus.BAD_REQUEST);
        }

        List<UserEntity> userEntities = userRepository.findByEmail_EmailOrUsername_UserName(email, userName);
        if (!userEntities.isEmpty()) {
            return userEntities.stream().map(userRepositoryConverter::mapToEntity).toList();
        }
        return List.of();
    }

    @Override
    public User saveUser(User user) {
        Objects.requireNonNull(user, "User cannot be null");
        UserEntity userEntity = userRepository.save(userRepositoryConverter.mapToTable(user));
        return userRepositoryConverter.mapToEntity(userEntity);

    }

    @Override
    public Optional<User> findByEmail(String email) {
        Objects.requireNonNull(email, "Email cannot be null");
        UserEntity userEntity = userRepository.findByEmail(new EmailEntity(email));
        return Optional.ofNullable(userRepositoryConverter.mapToEntity(userEntity));
    }
}
