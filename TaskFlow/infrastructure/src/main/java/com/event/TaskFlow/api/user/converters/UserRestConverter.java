package com.event.TaskFlow.api.user.converters;


import com.event.TaskFlow.api.role.converters.RoleRestConverter;
import com.event.TaskFlow.api.user.rest.UserRest;
import com.event.TaskFlow.shared.RestConverter;
import core.user.domain.Email;
import core.user.domain.Password;
import core.user.domain.User;
import core.user.domain.UserName;

public class UserRestConverter implements RestConverter<UserRest, User> {

    @Override
    public User mapToEntity(final UserRest rest) {

        if (rest.getId() != null) {
            return new User(rest.getId(), new UserName(rest.getUsername()), new Email(rest.getEmail()), new Password(rest.getPassword()),
                    new RoleRestConverter().mapToEntity(rest.getRole()));
        }

        return new User(new UserName(rest.getUsername()), new Email(rest.getEmail()), new Password(rest.getPassword()),
                new RoleRestConverter().mapToEntity(rest.getRole()));
    }

    @Override
    public UserRest mapToRest(final User entity) {
        return new UserRest(entity.getUsername().getUserName(), entity.getEmail().getEmail(), new RoleRestConverter().mapToRest(entity.getRole()));
    }
}
