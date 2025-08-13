package core.role.domain;

import core.shared.constants.CoreErrorCode;
import core.shared.exception.TaskFlowCoreException;

import java.io.Serializable;
import java.util.Objects;

public class Role implements Serializable {

    private Long id;
    private String name;
    private String description;

    public Role(Long id, String name, String description) {
        setId(id);
        setName(name);
        setDescription(description);
    }

    public Role( String name, String description) {
        setName(name);
        setDescription(description);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        if (id == null) throw new TaskFlowCoreException("ID is invalid", CoreErrorCode.EMPTY_DATA.getCode());;
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {

        if(description == null || description.isBlank())  throw new TaskFlowCoreException("Description is invalid", CoreErrorCode.EMPTY_DATA.getCode());

        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if(name == null || name.isBlank()) throw new TaskFlowCoreException("Name is invalid", CoreErrorCode.EMPTY_DATA.getCode());;
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return Objects.equals(id, role.id) && Objects.equals(name, role.name) && Objects.equals(description, role.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description);
    }
}
