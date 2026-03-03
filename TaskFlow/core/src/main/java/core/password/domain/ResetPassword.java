package core.password.domain;

import core.shared.constants.CoreErrorCode;
import core.shared.exception.TaskFlowCoreException;

import java.time.LocalDateTime;
import java.util.Objects;

public class ResetPassword {

    private Long id;
    private Long userID;
    private String resetToken;
    private String otp;
    private String requestIP;
    private String confirmIP;
    private String requestUserAgent;
    private String confirmUserAgent;
    private Boolean used = false;
    private LocalDateTime requestAt = LocalDateTime.now();
    private LocalDateTime confirmAt;
    private static final Long expirationDurationMinutes = 15L;
    private static final String IP_REGEX =
            "^((25[0-5]|2[0-4]\\d|[01]?\\d\\d?)\\.){3}" +
                    "(25[0-5]|2[0-4]\\d|[01]?\\d\\d?)$" +
                    "|^([0-9a-fA-F]{0,4}:){2,7}[0-9a-fA-F]{0,4}$";


    /**
     * Constructor for request reset Password
     *
     * @param id The reset password ID
     * @param userID The user ID
     * @param resetToken The reset token
     * @param otp The one-time password
     * @param requestIP The request IP address
     * @param requestUserAgent The request user agent
     */
    public ResetPassword(Long id, Long userID, String resetToken, String otp, String requestIP, String requestUserAgent) {
        setId(id);
        setUserID(userID);
        setResetToken(resetToken);
        setOtp(otp);
        setRequestIP(requestIP);
        setRequestUserAgent(requestUserAgent);
    }

    /**
     * Constructor for request reset Password
     * @param userID The user ID
     * @param resetToken The reset token
     * @param otp The one-time password
     * @param requestIP The request IP address
     * @param requestUserAgent The request user agent
     */
    public ResetPassword(Long userID, String resetToken, String otp, String requestIP, String requestUserAgent) {
        setUserID(userID);
        setResetToken(resetToken);
        setOtp(otp);
        setRequestIP(requestIP);
        setRequestUserAgent(requestUserAgent);
    }

    /**
     * Constructor for request reset Password
     *
     * @param id The reset password ID
     * @param userID The user ID
     * @param resetToken The reset token
     * @param otp The one-time password
     * @param requestIP The request IP address
     * @param confirmIP The confirmation IP address
     * @param requestUserAgent The request user agent
     * @param confirmUserAgent The confirmation user agent
     * @param requestAt The request time
     */
    public ResetPassword(Long id, Long userID,String resetToken, String otp, String requestIP, String confirmIP, String requestUserAgent, String confirmUserAgent, LocalDateTime requestAt) {
        setId(id);
        setUserID(userID);
        setResetToken(resetToken);
        setOtp(otp);
        setRequestIP(requestIP);
        setConfirmIP(confirmIP);
        setRequestUserAgent(requestUserAgent);
        setConfirmUserAgent(confirmUserAgent);
        this.requestAt = requestAt;
        setConfirmAt(LocalDateTime.now());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        if (id == null || id <= 0)
            throw new TaskFlowCoreException("ID is invalid", CoreErrorCode.EMPTY_DATA.getCode());

        this.id = id;
    }

    public Long getUserID() {
        return userID;
    }

    private void setUserID(Long userID) {
        if (userID == null || userID <= 0)
            throw new TaskFlowCoreException("ID is invalid", CoreErrorCode.EMPTY_DATA.getCode());

        this.userID = userID;
    }

    public String getResetToken() {
        return resetToken;
    }

    private void setResetToken(String resetToken) {
        if (resetToken == null || resetToken.isBlank())
            throw new TaskFlowCoreException("Token is invalid", CoreErrorCode.EMPTY_DATA.getCode());

        this.resetToken = resetToken;
    }

    public String getOtp() {
        return otp;
    }

    private void setOtp(String otp) {
        if (otp == null || otp.isBlank())
            throw new TaskFlowCoreException("OTP is invalid", CoreErrorCode.EMPTY_DATA.getCode());

        this.otp = otp;
    }

    public String getRequestIP() {
        return requestIP;
    }

    private void setRequestIP(String requestIP) {
        if (!validateIP(requestIP))
            throw new TaskFlowCoreException("Request IP is invalid", CoreErrorCode.INVALID_DATA.getCode());

        this.requestIP = requestIP;
    }

    public String getConfirmIP() {
        return confirmIP;
    }

    private void setConfirmIP(String confirmIP) {
        if (!validateIP(confirmIP))
            throw new TaskFlowCoreException("Request IP is invalid", CoreErrorCode.INVALID_DATA.getCode());

        this.confirmIP = confirmIP;
    }

    public String getRequestUserAgent() {
        return requestUserAgent;
    }

    private void setRequestUserAgent(String requestUserAgent) {
        if (!validateUserAgent(requestUserAgent))
            throw new TaskFlowCoreException("Request User Agent is invalid", CoreErrorCode.INVALID_DATA.getCode());

        this.requestUserAgent = requestUserAgent;
    }

    public String getConfirmUserAgent() {
        return confirmUserAgent;
    }

    private void setConfirmUserAgent(String confirmUserAgent) {
        if (!validateUserAgent(confirmUserAgent))
            throw new TaskFlowCoreException("Confirm User Agent is invalid", CoreErrorCode.INVALID_DATA.getCode());

        this.confirmUserAgent = confirmUserAgent;
    }

    public Boolean getUsed() {
        return used;
    }

    public LocalDateTime getRequestAt() {
        return requestAt;
    }

    public LocalDateTime getConfirmAt() {
        return confirmAt;
    }

    private void setConfirmAt(LocalDateTime confirmAt) {

        if (confirmAt == null)
            throw new TaskFlowCoreException("Time is invalid", CoreErrorCode.EMPTY_DATA.getCode());

        if(isExpired())
            throw new TaskFlowCoreException("Confirm time cannot be before request time", CoreErrorCode.INVALID_DATA.getCode());

        this.confirmAt = confirmAt;
    }

    private Boolean validateIP(String ip) {
        if (ip == null || ip.isBlank() || ip.length() > 45)
            return false;

        return ip.matches(IP_REGEX);
    }

    private Boolean validateUserAgent(String userAgent) {
        return userAgent != null && !userAgent.isBlank() && userAgent.length() <= 255;
    }

    public static Boolean validateOTP(String otp) {
        return otp != null && !otp.isBlank() && otp.length() == 6;
    }

    /**
     * Check if the reset password request has expired
     *
     * @return true if expired, false otherwise
     */
    public boolean isExpired() {
        if (requestAt == null) {
            return true;
        }
        LocalDateTime expirationTime = requestAt.plusMinutes(expirationDurationMinutes);
        return LocalDateTime.now().isAfter(expirationTime);
    }

    /**
     * Override equals method to compare ResetPassword objects based on resetToken
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ResetPassword resetPassword)) return false;
        return Objects.equals(resetToken, resetPassword.resetToken);
    }

    /**
     * Override hashCode method to generate hash based on resetToken
     * @return hash code
     */
    @Override
    public int hashCode() {
        return Objects.hash(resetToken);
    }
}
