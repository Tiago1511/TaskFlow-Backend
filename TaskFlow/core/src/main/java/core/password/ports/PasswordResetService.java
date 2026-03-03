package core.password.ports;

import core.password.domain.ResetPassword;

public interface PasswordResetService {

    ResetPassword saveResetPassword(ResetPassword resetPassword);
}
