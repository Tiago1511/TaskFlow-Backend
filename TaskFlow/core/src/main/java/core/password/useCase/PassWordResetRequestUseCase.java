package core.password.useCase;

import core.user.domain.Email;

public interface PassWordResetRequestUseCase {

    Boolean passWordResetRequest(Email email);
}
