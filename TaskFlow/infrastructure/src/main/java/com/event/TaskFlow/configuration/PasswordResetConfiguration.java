package com.event.TaskFlow.configuration;

import com.event.TaskFlow.email.SendEmailImpl;
import com.event.TaskFlow.persistence.converters.RoleRepositoryConverter;
import com.event.TaskFlow.persistence.impl.PasswordResetServiceImpl;
import com.event.TaskFlow.persistence.impl.UserServiceImpl;
import com.event.TaskFlow.persistence.repositories.UserRepository;
import core.password.useCase.PassWordResetRequestUseCaseImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class PasswordResetConfiguration {

    @Autowired
    private UserRepository userRepository;

    private final SendEmailImpl sendEmail = new SendEmailImpl();
    private final EncodeDecodeBase64Configuration encodeDecodeBase64 = new EncodeDecodeBase64Configuration();

    @Bean(name = "userRoleRepositoryConverterForPasswordReset")
    public RoleRepositoryConverter roleRepositoryConverter() {
        return new RoleRepositoryConverter();
    }

    @Bean
    public PasswordResetServiceImpl passwordResetService() {
        return new PasswordResetServiceImpl();
    }

    @Bean
    public PassWordResetRequestUseCaseImpl passWordResetRequestUseCase(UserServiceImpl userService, PasswordResetServiceImpl passwordResetService) {
        return new PassWordResetRequestUseCaseImpl(userService, encodeDecodeBase64, sendEmail, passwordResetService);
    }

}
