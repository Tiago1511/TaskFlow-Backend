package core.shared.constants;

/**
 * Enumeration representing internal error codes used in the Core layer of the application.
 * Each value corresponds to a specific type of business error and maps to a numeric internal code.
 */
public enum CoreErrorCode {

    EMPTY_DATA(400),
    DATA_ALREADY_EXISTS(422),
    RESOURCE_NOT_FOUND(404),
    UNKNOWN_ERROR(500),
    BAD_REQUEST(400),
    INVALID_DATA(422);

    private final int code;

    CoreErrorCode(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    /**
     * Retrieves the {@link CoreErrorCode} corresponding to the provided integer code.
     *
     * @param code the internal error code
     * @return the corresponding {@link CoreErrorCode}, or {@link #UNKNOWN_ERROR} if no match is found
     */
    public static CoreErrorCode fromCode(int code) {
        for (CoreErrorCode errorCode : values()) {
            if (errorCode.getCode() == code) {
                return errorCode;
            }
        }
        return UNKNOWN_ERROR;
    }
}

