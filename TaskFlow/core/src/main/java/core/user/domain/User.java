package core.user.domain;

import core.role.domain.Role;
import core.shared.constants.CoreErrorCode;
import core.shared.exception.TaskFlowCoreException;

import java.io.Serializable;
import java.util.Objects;

public class User implements Serializable {

    private Long id;
    private UserName username;
    private Email email;
    private Password password;
    private Role role;

    public User(Long id, UserName username, Email email, Password password, Role role) {
        setId(id);
        setUsername(username);
        setEmail(email);
        setPassword(password);
        setRole(role);
    }


    public User(UserName username, Email email, Password password, Role role) {
        setUsername(username);
        setEmail(email);
        setPassword(password);
        setRole(role);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        if (id == null || id <= 0) {
            throw new TaskFlowCoreException("ID is invalid", CoreErrorCode.EMPTY_DATA.getCode());
        }
        this.id = id;
    }

    public UserName getUsername() {
        return username;
    }

    public void setUsername(UserName username) {
        if (username == null) {
            throw new TaskFlowCoreException("User name cannot be empty", CoreErrorCode.EMPTY_DATA.getCode());
        }
        this.username = username;
    }

    public Email getEmail() {
        return this.email;
    }

    public void setEmail(Email email) {
        if (email == null) {
            throw new TaskFlowCoreException("Email cannot be empty", CoreErrorCode.EMPTY_DATA.getCode());
        }
        this.email = email;
    }

    public Password getPassword() {
        return password;
    }

    public void setPassword(Password password) {
        if (password == null) {
            throw new TaskFlowCoreException("Password cannot be empty", CoreErrorCode.EMPTY_DATA.getCode());
        }
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        if (role == null) {
            throw new TaskFlowCoreException("Role cannot be empty", CoreErrorCode.EMPTY_DATA.getCode());
        }
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id.equals(user.id) && username.equals(user.username) && email.equals(user.email) && password.equals(user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, email, password);
    }
}
