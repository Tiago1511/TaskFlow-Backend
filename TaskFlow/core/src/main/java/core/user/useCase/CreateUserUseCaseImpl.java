package core.user.useCase;

import core.role.ports.RoleRepositoryService;
import core.shared.constants.CoreErrorCode;
import core.shared.exception.TaskFlowCoreException;
import core.user.domain.User;
import core.user.ports.UserRepositoryService;

public class CreateUserUseCaseImpl implements CreateUserUseCase {

    private final UserRepositoryService userRepositoryService;
    private final RoleRepositoryService roleRepositoryService;

    public CreateUserUseCaseImpl(UserRepositoryService userRepositoryService, RoleRepositoryService roleRepositoryService) {
        this.userRepositoryService = userRepositoryService;
        this.roleRepositoryService = roleRepositoryService;
    }

    @Override
    public User createUser(User user) {

        if (!userRepositoryService.findByEmailOrUserName(user.getEmail().getEmail(), user.getUsername().getUserName()).isEmpty()) {
            throw new TaskFlowCoreException("User with this email or username already exists", CoreErrorCode.DATA_ALREADY_EXISTS.getCode());
        }

        user.setRole(roleRepositoryService.getRole(user.getRole().getName())
                    .orElseThrow(() -> new TaskFlowCoreException("Role not found", CoreErrorCode.RESOURCE_NOT_FOUND.getCode())));

        return userRepositoryService.saveUser(user);

    }
}
