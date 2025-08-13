package com.event.TaskFlow.configuration;

import com.event.TaskFlow.api.role.converters.RoleRestConverter;
import com.event.TaskFlow.persistence.converters.RoleRepositoryConverter;
import com.event.TaskFlow.persistence.impl.RoleServiceImpl;
import com.event.TaskFlow.persistence.repositories.RoleRepository;
import core.role.useCase.CreateRoleUseCaseImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RoleConfiguration {

    @Autowired
    private RoleRepository roleRepository;

    @Bean
    public RoleRepositoryConverter roleRepositoryConverter() {
        return new RoleRepositoryConverter();
    }

    @Bean
    public RoleRestConverter roleRestConverter() {
        return new RoleRestConverter();
    }

    @Bean
    public RoleServiceImpl roleService() {
        return new RoleServiceImpl(roleRepository, roleRepositoryConverter());
    }

    @Bean
    public CreateRoleUseCaseImpl createRoleUseCase() {
        return new CreateRoleUseCaseImpl(roleService());
    }
}
