package com.event.TaskFlow.email;

import core.password.ports.SendEmailService;
import org.springframework.beans.factory.annotation.Value;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import com.mailersend.sdk.emails.Email;
import com.mailersend.sdk.MailerSend;
import com.mailersend.sdk.MailerSendResponse;
import com.mailersend.sdk.exceptions.MailerSendException;
import com.mailersend.sdk.*;

public class SendEmailImpl implements SendEmailService {


    private static final String FRONTEND_RESET_Password_URL = "";

    @Value("${mailersend.api.key}")
    private String apiKey;

    @Value("${mailersend.email}")
    private String fromEmail;

    @Override
    public void sendPasswordResetEmail(String to, String resetToken, String otp) {
        String body = buildResetEmailBody(resetToken, otp);

        Email email = new Email();

        email.setFrom("name", fromEmail);
        email.addRecipient("name", to);

        email.setSubject("Password Reset Request");

        email.setHtml(body);

        MailerSend ms = new MailerSend();

        ms.setToken(apiKey);

        try {
            MailerSendResponse response = ms.emails().send(email);
            System.out.println(response.messageId);
        } catch (MailerSendException e) {
            e.printStackTrace();
        }
    }

    /**
     * Builds the HTML body for the password reset email, including the reset link and OTP.
     * @param resetToken The token to be included in the reset link
     * @param otp The one-time password to be displayed in the email
     * @return A string containing the HTML content of the email
     */
    private String buildResetEmailBody(String resetToken, String otp) {
        String encodedToken;
        try {
            encodedToken = URLEncoder.encode(resetToken, StandardCharsets.UTF_8);
        } catch (Exception e) {
            // Fallback to raw token if encoding fails
            encodedToken = resetToken;
        }

        String resetLink = FRONTEND_RESET_Password_URL + "?token=" + encodedToken;

        return "<!DOCTYPE html>" +
                "<html>" +
                "<head><meta charset=\"UTF-8\"><title>Reset your password</title></head>" +
                "<body style=\"font-family:Arial,sans-serif;line-height:1.6;color:#333;\">\n" +
                "<h2>Password Reset Request</h2>" +
                "<p>We received a request to reset the password for your account. Use the button below to reset your password. Your one-time password (OTP) is shown below and will be required in the reset flow.</p>" +
                "<p style=\"font-size:18px;font-weight:bold;\">OTP: <span style=\"display:inline-block;padding:6px 10px;background:#f4f4f4;border-radius:4px;\">" + otp + "</span></p>" +
                "<p style=\"text-align:center;margin:30px 0;\"><a href=\"" + resetLink + "\" style=\"background:#1a73e8;color:#fff;padding:12px 20px;text-decoration:none;border-radius:6px;display:inline-block;\">Reset password</a></p>" +
                "<p>If the button doesn't work, copy and paste the following link into your browser:</p>" +
                "<p><a href=\"" + resetLink + "\">" + resetLink + "</a></p>" +
                "<p>If you didn't request a password reset, ignore this email.</p>" +
                "</body></html>";
    }
}
