package core.user.useCase;

import core.shared.constants.CoreErrorCode;
import core.shared.exception.TaskFlowCoreException;
import core.user.domain.User;
import core.user.ports.UserRepositoryService;

public class CreateUserUseCaseImpl implements CreateUserUseCase {

    private final UserRepositoryService userRepositoryService;

    public CreateUserUseCaseImpl(UserRepositoryService userRepositoryService) {
        this.userRepositoryService = userRepositoryService;
    }

    @Override
    public User createUser(User user) {

        if (!userRepositoryService.findByEmailOrUserName(user.getEmail().getEmail(), user.getUsername().getUserName()).isEmpty()) {
            throw new TaskFlowCoreException("User with this email or username already exists", CoreErrorCode.DATA_ALREADY_EXISTS.getCode());
        }

        return userRepositoryService.saveUser(user);

    }
}
