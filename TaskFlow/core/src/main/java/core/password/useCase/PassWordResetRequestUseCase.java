package core.password.useCase;

import core.user.domain.Email;

public interface PassWordResetRequestUseCase {

    void passWordResetRequest(Email email, String requestIP, String requestUserAgent);
}
