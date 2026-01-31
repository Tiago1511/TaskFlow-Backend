package com.event.TaskFlow.persistence.converters;

import com.event.TaskFlow.persistence.entities.*;
import com.event.TaskFlow.shared.RepositoryConverter;
import core.user.domain.Email;
import core.user.domain.Password;
import core.user.domain.User;
import core.user.domain.UserName;

public class UserRepositoryConverter implements RepositoryConverter<UserEntity, User> {

    private final RoleRepositoryConverter roleRepositoryConverter;

    public UserRepositoryConverter(RoleRepositoryConverter roleRepositoryConverter) {
        this.roleRepositoryConverter = roleRepositoryConverter;
    }

    @Override
    public UserEntity mapToTable(final User persistenceObject) {
        return new UserEntity(persistenceObject.getId(), new UserNameEntity(persistenceObject.getUsername().getUserName()), new PasswordEntity(persistenceObject.getPassword().getPassword()), new EmailEntity(persistenceObject.getEmail().getEmail()), roleRepositoryConverter.mapToTable(persistenceObject.getRole()));
    }

    @Override
    public User mapToEntity(final UserEntity entityObject) {
        return new User(entityObject.getId(), new UserName(entityObject.getUsername().getUserName()), new Email(entityObject.getEmail().getEmail()), Password.fromEncoded(entityObject.getPassword().getPassword()), roleRepositoryConverter.mapToEntity(entityObject.getRole()));
    }
}
