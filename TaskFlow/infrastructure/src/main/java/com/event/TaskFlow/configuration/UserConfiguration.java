package com.event.TaskFlow.configuration;

import com.event.TaskFlow.api.user.converters.UserRestConverter;
import com.event.TaskFlow.persistence.converters.RoleRepositoryConverter;
import com.event.TaskFlow.persistence.converters.UserRepositoryConverter;
import com.event.TaskFlow.persistence.impl.RoleServiceImpl;
import com.event.TaskFlow.persistence.impl.UserServiceImpl;
import com.event.TaskFlow.persistence.repositories.RoleRepository;
import com.event.TaskFlow.persistence.repositories.UserRepository;
import core.user.useCase.CreateUserUseCaseImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class UserConfiguration {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptEncoderService passwordEncoder;

    @Bean(name = "userRoleRepositoryConverter")
    public RoleRepositoryConverter roleRepositoryConverter() {
        return new RoleRepositoryConverter();
    }

    @Bean
    public UserRepositoryConverter userRepositoryConverter() {
        return new UserRepositoryConverter(roleRepositoryConverter());
    }

    @Bean
    public UserRestConverter userRestConverter() {
        return new UserRestConverter();
    }

    @Bean
    public UserServiceImpl userService() {
        return new UserServiceImpl(userRepository, userRepositoryConverter());
    }

    @Bean (name = "userRoleServiceImpl")
    public RoleServiceImpl roleService() {
        return new RoleServiceImpl(roleRepository, roleRepositoryConverter());
    }

    @Bean
    public CreateUserUseCaseImpl createUserUseCase() {
        return new CreateUserUseCaseImpl(userService(), roleService(), passwordEncoder);
    }
}