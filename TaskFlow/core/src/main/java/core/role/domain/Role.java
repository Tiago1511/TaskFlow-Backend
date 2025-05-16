package core.role.domain;

import java.io.Serializable;

public class Role implements Serializable {

    private Long id;
    private String name;
    private String description;

    public Role(Long id, String name, String description) {
        setId(id);
        setName(name);
        setDescription(description);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        if (id == null) return;
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {

        if(description == null || name.isBlank()) return;

        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if(name == null || name.isBlank() ) return;
        this.name = name;
    }
}
