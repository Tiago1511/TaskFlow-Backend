package core.password.useCase;

import core.password.domain.ResetPassword;
import core.password.ports.SendEmailService;
import core.user.domain.Email;
import core.user.domain.User;
import core.user.ports.EncoderService;
import core.user.ports.UserRepositoryService;

import java.util.Optional;
import java.util.UUID;

public class PassWordResetRequestUseCaseImpl implements PassWordResetRequestUseCase {

    private final UserRepositoryService userRepositoryService;
    private final EncoderService encoderService;
    private final SendEmailService sendEmailService;

    public PassWordResetRequestUseCaseImpl (UserRepositoryService userRepositoryService, EncoderService encoderService, SendEmailService sendEmailService) {
        this.userRepositoryService = userRepositoryService;
        this.encoderService = encoderService;
        this.sendEmailService = sendEmailService;
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

            //TODO: Save resetPassword to repository and send email with resetToken and otp

            sendEmailService.sendPasswordResetEmail(email.getEmail(), resetToken, otp);

        }

    }

    // Generate a 6-digit OTP
    private String generateOTP() {
        int otpValue = (int) (Math.random() * 1000000);
        return String.format("%06d", otpValue);
    }
}
