package core.role.useCase;

import core.role.domain.Role;

import java.util.Arrays;
import java.util.List;

public class GetRolesUseCaseImpl implements GetRolesUseCase{
    @Override
    public List<Role> getAllRoles() {
        return List.of(Role.values());
    }

    @Override
    public List<Role> getNonAdminRoles() {
        return Arrays.stream(Role.values()) .filter(role -> !role.getAdmin()) .toList();
    }
}
