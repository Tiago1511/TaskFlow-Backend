package core.role.useCase;

import core.role.domain.Role;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions.*;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import java.util.List;

class GetRolesUseCaseImplTest {

    @InjectMocks
    private GetRolesUseCaseImpl createUserUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("Get All Roles")
    void getAllRoles() {
        List<Role> roles = createUserUseCase.getAllRoles();
        assertEquals(3, roles.size());
    }

    @Test
    @DisplayName("Get Non Admin Roles")
    void getNonAdminRoles() {
        List<Role> roleList = createUserUseCase.getNonAdminRoles();

        assertEquals(2, roleList.size());

        assertTrue(
                roleList.stream().noneMatch(Role::getAdmin)
        );
    }
}