package core.role.useCase;

import core.role.domain.Role;
import core.role.ports.RoleRepositoryService;
import core.shared.exception.TaskFlowCoreException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class CreateRoleUseCaseImplTest {

    @Mock
    private RoleRepositoryService roleRepositoryService;

    @InjectMocks
    private CreateRoleUseCaseImpl roleService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("Create role successfully")
    void createRole() {
        Role role = new Role(1L, "Admin", "Admin description");
        when(roleRepositoryService.getRole("Admin")).thenReturn(Collections.emptyList());
        when(roleRepositoryService.saveRole(role)).thenReturn(role);

        Role result = roleService.createRole(role);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(role.getId(), result.getId());
        verify(roleRepositoryService, times(1)).getRole("Admin");
        verify(roleRepositoryService, times(1)).saveRole(role);
    }

    @Test
    @DisplayName("Has role exist")
    void haveRole() {
        Role role = new Role(1L, "Admin", "Admin description");
        Role existingRole = new Role(1L, "Admin", "Admin All Permission");

        when(roleRepositoryService.getRole("Admin")).thenReturn(Collections.singletonList(existingRole));

        TaskFlowCoreException exception = assertThrows(TaskFlowCoreException.class, () -> {
            roleService.createRole(role);
        });

        verify(roleRepositoryService, times(1)).getRole("Admin");
        verify(roleRepositoryService, times(0)).saveRole(any());

        Assertions.assertEquals("This role already exists", exception.getMessage());
    }
}