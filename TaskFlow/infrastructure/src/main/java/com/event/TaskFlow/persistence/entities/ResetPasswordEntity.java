package com.event.TaskFlow.persistence.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "RESET_PASSWORD")
public class ResetPasswordEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "resetPasswordSeq")
    private Long id;

    @NotBlank(message = "User cannot be blank")
    @Column(name = "userID", nullable = false)
    private Long userID;

    @NotBlank(message = "Reset token cannot be blank")
    @Column(name = "resetToken", nullable = false)
    private String resetToken;

    @NotBlank(message = "OTP cannot be blank")
    private String otp;

    @NotBlank(message = "IP cannot be blank")
    @Column(name = "requestIP", nullable = false)
    private String requestIP;

    @Column(name = "confirmIP", nullable = false)
    private String confirmIP;

    @NotBlank
    private String requestUserAgent;
    private String confirmUserAgent;

    @NotBlank
    private Boolean used;

    @NotBlank
    private LocalDateTime requestAt;
    private LocalDateTime confirmAt;

    protected ResetPasswordEntity() {

    }

    public ResetPasswordEntity(Long id, Long userId, String resetToken, String otp, String requestIP, String requestUserAgent, Boolean used, LocalDateTime requestAt) {
        this.id = id;
        this.userID = userId;
        this.resetToken = resetToken;
        this.otp = otp;
        this.requestIP = requestIP;
        this.requestUserAgent = requestUserAgent;
        this.used = used;
        this.requestAt = requestAt;
    }

    public ResetPasswordEntity(Long id, Long userId, String resetToken, String otp, String requestIP, String confirmIP, String requestUserAgent, String confirmUserAgent, Boolean used, LocalDateTime requestAt, LocalDateTime confirmAt) {
        this.id = id;
        this.userID = userId;
        this.resetToken = resetToken;
        this.otp = otp;
        this.requestIP = requestIP;
        this.confirmIP = confirmIP;
        this.requestUserAgent = requestUserAgent;
        this.confirmUserAgent = confirmUserAgent;
        this.used = used;
        this.requestAt = requestAt;
        this.confirmAt = confirmAt;
    }

    public Long getId() {
        return id;
    }

    public Long getUserID() {
        return userID;
    }

    public String getResetToken() {
        return resetToken;
    }

    public String getOtp() {
        return otp;
    }

    public String getRequestIP() {
        return requestIP;
    }

    public String getRequestUserAgent() {
        return requestUserAgent;
    }

    public String getConfirmIP() {
        return confirmIP;
    }

    public String getConfirmUserAgent() {
        return confirmUserAgent;
    }

    public Boolean getUsed() {
        return used;
    }

    public LocalDateTime getConfirmAt() {
        return confirmAt;
    }

    public LocalDateTime getRequestAt() {
        return requestAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ResetPasswordEntity that = (ResetPasswordEntity) o;
        return id.equals(that.id) && userID.equals(that.userID) && resetToken.equals(that.resetToken) && otp.equals(that.otp) && requestIP.equals(that.requestIP) && requestUserAgent.equals(that.requestUserAgent) && requestAt.equals(that.requestAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userID, resetToken, otp, requestIP, requestUserAgent, requestAt);
    }

}
