package com.event.TaskFlow.persistence.impl;

import com.event.TaskFlow.api.role.impl.RoleControllerImpl;
import com.event.TaskFlow.persistence.converters.RoleRepositoryConverter;
import com.event.TaskFlow.persistence.entities.RoleEntity;
import com.event.TaskFlow.persistence.repositories.RoleRepository;
import core.role.domain.Role;
import core.role.useCase.CreateRoleUseCaseImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RoleServiceImplTest {

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private RoleRepositoryConverter roleRepositoryConverter;

    @InjectMocks
    private RoleServiceImpl roleService;

    private AutoCloseable mocks;

    @BeforeEach
    void setUp()  {
        mocks = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() throws Exception {
        mocks.close();
    }

    @Test
    @DisplayName("Get Role by name")
    void getRole() {
        Role expectedRole = new Role(1L, "Admin", "Admin description");
        RoleEntity entity = new RoleEntity(1L, "Admin", "Admin description");

        when(roleRepository.findRoleEntitiesByName("Admin"))
                .thenReturn(Optional.of(entity));
        when(roleRepositoryConverter.mapToEntity(entity)).thenReturn(expectedRole);

        Optional<Role> role = roleService.getRole("Admin");

        assertTrue(role.isPresent());
        assertEquals(expectedRole.getId(), role.get().getId());
        assertEquals(expectedRole.getName(), role.get().getName());
        assertEquals(expectedRole.getDescription(), role.get().getDescription());

        verify(roleRepository, times(1)).findRoleEntitiesByName("Admin");
    }

    @Test
    @DisplayName("Save Role")
    void saveRole() {
        Role role = new Role(1L, "Admin", "Admin description");
        RoleEntity entity = new RoleEntity(1L, "Admin", "Admin description");

        when(roleRepositoryConverter.mapToTable(role)).thenReturn(entity);
        when(roleRepository.save(entity)).thenReturn(entity);
        when(roleRepositoryConverter.mapToEntity(entity)).thenReturn(role);

        Role savedRole = roleService.saveRole(role);

        assertNotNull(savedRole);
        assertEquals(role.getId(), savedRole.getId());
        assertEquals(role.getName(), savedRole.getName());
        assertEquals(role.getDescription(), savedRole.getDescription());

        verify(roleRepositoryConverter, times(1)).mapToTable(role);
        verify(roleRepository, times(1)).save(entity);
        verify(roleRepositoryConverter, times(1)).mapToEntity(entity);
    }

    @Test
    @DisplayName("Get Role by name - Not Found")
    void getRoleNotFound() {
        when(roleRepository.findRoleEntitiesByName("NonExistentRole"))
                .thenReturn(Optional.empty());

        Optional<Role> role = roleService.getRole("NonExistentRole");

        assertFalse(role.isPresent());

        verify(roleRepository, times(1)).findRoleEntitiesByName("NonExistentRole");
    }

    @Test
    @DisplayName("Save Role - Null Role")
    void saveRoleNull() {
        assertThrows(NullPointerException.class, () -> {
            roleService.saveRole(null);
        });

        verify(roleRepositoryConverter, never()).mapToTable(any());
        verify(roleRepository, never()).save(any());
        verify(roleRepositoryConverter, never()).mapToEntity(any());
    }
}