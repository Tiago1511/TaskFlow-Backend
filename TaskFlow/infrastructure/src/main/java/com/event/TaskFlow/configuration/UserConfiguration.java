package com.event.TaskFlow.configuration;

import com.event.TaskFlow.api.user.converters.UserRestConverter;
import com.event.TaskFlow.persistence.converters.UserRepositoryConverter;
import com.event.TaskFlow.persistence.impl.UserServiceImpl;
import com.event.TaskFlow.persistence.repositories.UserRepository;
import core.user.useCase.CreateUserUseCaseImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserConfiguration {

    @Autowired
    private UserRepository userRepository;

    @Bean
    public UserRepositoryConverter userRepositoryConverter() {
        return new UserRepositoryConverter();
    }

    @Bean
    public UserRestConverter userRestConverter() {
        return new UserRestConverter();
    }

    @Bean
    public UserServiceImpl userService() {
        return new UserServiceImpl(userRepository, userRepositoryConverter());
    }

    @Bean
    public CreateUserUseCaseImpl createUserUseCase() {
        return new CreateUserUseCaseImpl(userService());
    }
}