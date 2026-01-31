package com.event.TaskFlow.configuration;

import com.event.TaskFlow.api.role.converters.RoleRestConverter;
import core.role.useCase.GetRolesUseCaseImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RoleConfiguration {

    @Bean
    public RoleRestConverter roleRestConverter() {
        return new RoleRestConverter();
    }

    @Bean
    public GetRolesUseCaseImpl getRolesUseCase() {
        return new GetRolesUseCaseImpl();
    }
}
