package core.role.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RoleTest {

    @Test
    @DisplayName("Get role name")
    void getName() {
        String roleName = "Admin";
        assertEquals(roleName, Role.ADMIN.getName());
        assertNotEquals(roleName, Role.MEMBER.getName());
    }

    @Test
    @DisplayName("Get role description")
    void getDescription() {
        String roleName = "All permisions";
        assertEquals(roleName, Role.ADMIN.getDescription());
        assertNotEquals(roleName, Role.MEMBER.getDescription());
    }

    @Test
    @DisplayName("Admin Role")
    void isAdmin() {
        assertTrue(Role.ADMIN.getAdmin());
    }

    @Test
    @DisplayName("No Admin Role")
    void noAdmin() {
        assertFalse(Role.MEMBER.getAdmin());
        assertFalse(Role.PRODUCT_OWNER.getAdmin());
    }
}