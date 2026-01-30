package core.user.domain;

import core.shared.constants.CoreErrorCode;
import core.shared.exception.TaskFlowCoreException;

import java.io.Serializable;
import java.util.Objects;

public class UserName{

    private String userName;
    private static final String regexPattern = "^[a-zA-Z0-9_]{1,50}$";

    public UserName(String name) {
        setUserName(name);
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String name) {
        if (name == null || name.isBlank()) {
            throw new TaskFlowCoreException("User name cannot be empty", CoreErrorCode.EMPTY_DATA.getCode());
        } else if (!name.matches(regexPattern)) {
            throw new TaskFlowCoreException("User name is invalid", CoreErrorCode.INVALID_DATA.getCode());
        }

        this.userName = name;
    }


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        UserName userNameObject = (UserName) o;
        return Objects.equals(userName, userNameObject.userName);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(userName);
    }
}
