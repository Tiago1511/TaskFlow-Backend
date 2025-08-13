package core.role.domain;

import core.shared.exception.TaskFlowCoreException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class RoleTest {

    Role role;

    @BeforeEach
    void setUp() {
        role = new Role(1L,"Admin", "All permission");
    }

    @Test
    @DisplayName("Get id role")
    void getId() {
        Assertions.assertEquals(1L, role.getId());
    }

    @Test
    @DisplayName("Set id role")
    void setId() {
        role.setId(2L);
        Assertions.assertEquals(2L, role.getId());
    }

    @Test
    @DisplayName("Get description role")
    void getDescription() {
        Assertions.assertEquals("All permission", role.getDescription());
    }

    @Test
    @DisplayName("Set description role")
    void setDescription() {
        role.setDescription("Admin permission changed");
        Assertions.assertEquals("Admin permission changed", role.getDescription());
    }

    @Test
    @DisplayName("Get name role")
    void getName() {
        Assertions.assertEquals("Admin", role.getName());
    }

    @Test
    @DisplayName("Set name role")
    void setName() {
        role.setName("Admin changed");
        Assertions.assertEquals("Admin changed", role.getName());
    }

    @Test
    @DisplayName("Empty Name")
    void emptyName() {
        Exception exception = assertThrows(TaskFlowCoreException.class, () -> {
            role.setName("");
        });
        Assertions.assertEquals("Name is invalid", exception.getMessage());
    }

    @Test
    @DisplayName("Empty Description")
    void emptyDescription() {
        Exception exception = assertThrows(TaskFlowCoreException.class, () -> {
            role.setDescription("");
        });
        Assertions.assertEquals("Description is invalid", exception.getMessage());
    }

    @Test
    @DisplayName("Empty ID")
    void emptyId() {
        Exception exception = assertThrows(TaskFlowCoreException.class, () -> {
            role.setId(null);
        });
        Assertions.assertEquals("ID is invalid", exception.getMessage());
    }

    @Test
    @DisplayName("Space Name")
    void spaceName() {
        Exception exception = assertThrows(TaskFlowCoreException.class, () -> {
            role.setName(" ");
        });
        Assertions.assertEquals("Name is invalid", exception.getMessage());
    }

    @Test
    @DisplayName("Space Description")
    void spaceDescription() {
        Exception exception = assertThrows(TaskFlowCoreException.class, () -> {
            role.setDescription(" ");
        });
        Assertions.assertEquals("Description is invalid", exception.getMessage());
    }

    @Test
    @DisplayName("Null Name")
    void nullName() {
        Exception exception = assertThrows(TaskFlowCoreException.class, () -> {
            role.setName(null);
        });
        Assertions.assertEquals("Name is invalid", exception.getMessage());
    }

    @Test
    @DisplayName("Null Description")
    void nullDescription() {
        Exception exception = assertThrows(TaskFlowCoreException.class, () -> {
            role.setDescription(null);
        });
        Assertions.assertEquals("Description is invalid", exception.getMessage());
    }

    @Test
    @DisplayName("Equals Role")
    void equalsRole() {
        Role role2 = new Role(1L, "Admin", "All permission");
        Assertions.assertEquals(role, role2);
    }

    @Test
    @DisplayName("Not Equals Role")
    void notEqualsRole() {
        Role role2 = new Role(2L, "User", "Limited permission");
        Assertions.assertNotEquals(role, role2);
    }

    @Test
    @DisplayName("Not Equals Null")
    void notEqualsNull() {
        Assertions.assertNotEquals(null, role);
    }

    @Test
    @DisplayName("Not Equals Different Class")
    void notEqualsDifferentClass() {
        Assertions.assertNotEquals(new Object(), role);
    }

    @Test
    @DisplayName("Hash Code Role")
    void hashCodeRole() {
        Role role2 = new Role(1L, "Admin", "All permission");
        Assertions.assertEquals(role.hashCode(), role2.hashCode());
    }

    @Test
    @DisplayName("Hash Code Not Equals Role")
    void hashCodeNotEqualsRole() {
        Role role2 = new Role(2L, "User", "Limited permission");
        Assertions.assertNotEquals(role.hashCode(), role2.hashCode());
    }
}