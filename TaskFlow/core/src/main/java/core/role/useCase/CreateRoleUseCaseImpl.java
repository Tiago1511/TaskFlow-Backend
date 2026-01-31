package core.role.useCase;

import core.role.domain.Role;
import core.role.ports.RoleRepositoryService;
import core.shared.constants.CoreErrorCode;
import core.shared.exception.TaskFlowCoreException;

public class CreateRoleUseCaseImpl implements CreateRoleUseCase {

    private final RoleRepositoryService roleRepositoryService;

    public CreateRoleUseCaseImpl(RoleRepositoryService roleRepositoryService) {
        this.roleRepositoryService = roleRepositoryService;
    }

    @Override
    public Role createRole(Role role) {

        if(!roleRepositoryService.getRole(role.getName()).isEmpty()) {
            throw new TaskFlowCoreException("This role already exists", CoreErrorCode.DATA_ALREADY_EXISTS.getCode());
        }

        return roleRepositoryService.saveRole(role);
    }
}
