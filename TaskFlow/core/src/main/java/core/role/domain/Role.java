package core.role.domain;

public enum Role {

    ADMIN("Admin", "All permissions", true),
    PRODUCT_OWNER("Product Owner", "Create project and organize tasks", false),
    MEMBER("Member", "Work on assigned tasks", false);

    private final String name;
    private final String description;
    private final Boolean isAdmin;
    Role (String name, String description, Boolean isAdmin) {
        this.name = name;
        this.description = description;
        this.isAdmin = isAdmin;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Boolean getAdmin() {
        return isAdmin;
    }
}
