package core.password.useCase;

import core.password.domain.ResetPassword;
import core.password.ports.EncodeDecodeBase64Service;
import core.password.ports.PasswordResetService;
import core.password.ports.SendEmailService;
import core.user.domain.Email;
import core.user.domain.User;
import core.user.ports.UserRepositoryService;

import java.util.Optional;
import java.util.UUID;

public class PassWordResetRequestUseCaseImpl implements PassWordResetRequestUseCase {

    private final UserRepositoryService userRepositoryService;
    private final EncodeDecodeBase64Service encoderService;
    private final SendEmailService sendEmailService;
    private final PasswordResetService passwordResetService;

    public PassWordResetRequestUseCaseImpl (UserRepositoryService userRepositoryService, EncodeDecodeBase64Service encoderService, SendEmailService sendEmailService, PasswordResetService passwordResetService) {
        this.userRepositoryService = userRepositoryService;
        this.encoderService = encoderService;
        this.sendEmailService = sendEmailService;
        this.passwordResetService = passwordResetService;
    }

    @Override
    public void passWordResetRequest(Email email, String requestIP, String requestUserAgent) {

        Optional<User> user = userRepositoryService.findByEmail(email.getEmail());

        if (user.isPresent()) {

            String resetToken = String.valueOf(UUID.randomUUID());
            String otp = generateOTP();

            if(!ResetPassword.validateOTP(otp)){
                return;
            }

            ResetPassword resetPassword = new ResetPassword(user.get().getId(), resetToken, encoderService.encode(otp), requestIP, requestUserAgent);

            passwordResetService.saveResetPassword(resetPassword);
            sendEmailService.sendPasswordResetEmail(email.getEmail(), resetToken, otp);

        }
    }

    // Generate a 6-digit OTP
    private String generateOTP() {
        int otpValue = (int) (Math.random() * 1000000);
        return String.format("%06d", otpValue);
    }
}
