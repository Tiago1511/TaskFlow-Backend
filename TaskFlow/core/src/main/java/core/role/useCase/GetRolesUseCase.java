package core.role.useCase;

import core.role.domain.Role;

import java.util.List;

public interface GetRolesUseCase {
    List<Role> getAllRoles();
    List<Role> getNonAdminRoles();
}
