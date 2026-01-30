package core.user.domain;

import core.shared.constants.CoreErrorCode;
import core.shared.exception.TaskFlowCoreException;

import java.util.Objects;

public class Password  {

    private String password;
    private static final String regexPattern = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[!@#$%^&*(),.?\":{}|<>]).{8,100}$";

    public Password(String password) {
        setPassword(password);
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        if (password == null || password.isBlank()) {
            throw new TaskFlowCoreException("Password cannot be empty", CoreErrorCode.EMPTY_DATA.getCode());
        } else if (!password.matches(regexPattern)) {
            throw new TaskFlowCoreException("Password must be between 8 and 100 characters long, contain at least one uppercase letter, one lowercase letter, one digit, and one special character", CoreErrorCode.INVALID_DATA.getCode());
        }
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Password password1 = (Password) o;
        return password.equals(password1.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(password);
    }
}
