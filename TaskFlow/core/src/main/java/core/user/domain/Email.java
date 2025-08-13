package core.user.domain;

import core.shared.constants.CoreErrorCode;
import core.shared.exception.TaskFlowCoreException;

import java.io.Serializable;
import java.util.Objects;

public class Email implements Serializable {

    private String email;
    private static final String regexPattern = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";

    public Email(String email) {
        setEmail(email);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (email == null || email.isBlank()) {
            throw new TaskFlowCoreException("Email cannot be empty", CoreErrorCode.EMPTY_DATA.getCode());
        }else if (!email.matches(regexPattern)) {
            throw new TaskFlowCoreException("Email format is invalid", CoreErrorCode.INVALID_DATA.getCode());
        }
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Email email = (Email) o;
        return Objects.equals(this.email, email.email);
    }

    @Override
    public int hashCode() {
        return email.hashCode();
    }
}
