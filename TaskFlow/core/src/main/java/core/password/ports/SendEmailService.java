package core.password.ports;

public interface SendEmailService {

        void sendPasswordResetEmail(String to, String resetToken, String otp);
}
