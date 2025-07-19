package com.event.TaskFlow.shared.exception;

import com.event.TaskFlow.api.TaskFlowResponse;
import com.event.TaskFlow.shared.Constants.CommonConstants;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestExceptionHandler {

    /*@ExceptionHandler(TaskFlowException.class)
    public ProblemDetail handlerTaskFlowException(TaskFlowException e) {
        return e.toProblemDetail();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail handlerMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        var fieldErrors = e.getFieldErrors().stream()
                .map(f -> new FieldError(f.getObjectName(), f.getField(), f.getDefaultMessage()))
                .toList();
        var problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        problemDetail.setTitle("Validation Error");
        problemDetail.setProperty("invalid-field-value",fieldErrors);
        return problemDetail;
    }*/

    /**
     * Handler for {@link TaskFlowException}.
     * This method catches exceptions of type {@link TaskFlowException} and returns a response with HTTP status 400 (Bad Request).
     *
     * @param e The {@link TaskFlowException} exception thrown.
     * @return An instance of {@link TaskFlowResponse} containing error details.
     */
    @ExceptionHandler(TaskFlowException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public TaskFlowResponse<Void> handlerTaskFlowException(TaskFlowException e) {
        return new TaskFlowResponse<>(CommonConstants.ERROR, String.valueOf(HttpStatus.BAD_REQUEST), null);
    }

    /**
     * Handler for {@link MethodArgumentNotValidException}.
     * This method catches exceptions thrown when method argument validation fails and returns a response with HTTP status 400 (Bad Request).
     *
     * @param e The {@link MethodArgumentNotValidException} exception thrown.
     * @return An instance of {@link TaskFlowResponse} containing validation error details.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public TaskFlowResponse<Void> handlerMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        return new TaskFlowResponse<>(CommonConstants.ERROR, String.valueOf(HttpStatus.BAD_REQUEST), null);
    }

    /**
     * Handler for {@link BadRequestException}.
     * This method catches exceptions of type {@link BadRequestException} and returns a response with HTTP status 400 (Bad Request),
     * including the exception's title and message.
     *
     * @param e The {@link BadRequestException} exception thrown.
     * @return An instance of {@link TaskFlowResponse} containing detailed error information, including the exception's title and message.
     */
    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public TaskFlowResponse<Void> handlerBadRequestException(BadRequestException e) {
        return new TaskFlowResponse<>(CommonConstants.ERROR, String.valueOf(HttpStatus.BAD_REQUEST), e.getTitle() + "/n" + e.getDetail());
    }

    /**
     * Handler for {@link DataAlreadyExistsException}.
     * This method catches exceptions thrown when there is an attempt to insert data that already exists, returning a response with HTTP status 422 (Unprocessable Entity),
     * including the exception's title and message.
     *
     * @param e The {@link DataAlreadyExistsException} exception thrown.
     * @return An instance of {@link TaskFlowResponse} containing detailed error information about the duplicate data issue, including the exception's title and message.
     */
    @ExceptionHandler(DataAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public TaskFlowResponse<Void> handlerDataAlreadyExistsException(DataAlreadyExistsException e) {
        return new TaskFlowResponse<>(CommonConstants.ERROR, String.valueOf(HttpStatus.UNPROCESSABLE_ENTITY), e.getTitle() + "/n" + e.getDetail());
    }

    /**
     * Handles {@link IllegalArgumentException}, which typically occurs when a method receives
     * an illegal or inappropriate argument.
     *
     * <p>This exception may be thrown by manual validations, mappers, or service logic
     * when input values do not meet the expected criteria.</p>
     *
     * <p>Returns an HTTP 400 (Bad Request) response indicating invalid input.</p>
     *
     * @param ex the {@link IllegalArgumentException} thrown
     * @return a {@link TaskFlowResponse} containing the error message and HTTP 400 status
     */
    @ExceptionHandler({IllegalArgumentException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public TaskFlowResponse<Void> handleIllegalArgumentException(IllegalArgumentException ex) {
        return new TaskFlowResponse<>(CommonConstants.ERROR, String.valueOf(HttpStatus.BAD_REQUEST), "Invalid input: " + ex.getMessage());
    }

    /**
     * Global exception handler for uncaught exceptions of type {@link Exception}.
     *
     * <p>This serves as a fallback for any unexpected or unhandled exceptions,
     * ensuring that the application does not expose stack traces or internal errors
     * directly to the client.</p>
     *
     * <p>Returns an HTTP 500 (Internal Server Error) response indicating an unexpected server failure.</p>
     *
     * @param ex the unexpected {@link Exception} thrown
     * @return a {@link TaskFlowResponse} with a generic error message and HTTP 500 status
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public TaskFlowResponse<Void> handleUnexpectedException(Exception ex) {
        return new TaskFlowResponse<>(CommonConstants.ERROR, String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR), "An unexpected error occurred");
    }


    /**
     * Handles {@link HttpMessageNotReadableException}, which is thrown when the incoming HTTP request body
     * cannot be parsed or read correctly, typically due to malformed JSON or incorrect data types.
     *
     * <p>This commonly occurs when the client sends an invalid or incomplete JSON payload.</p>
     *
     * <p>Returns an HTTP 400 (Bad Request) response indicating that the request could not be interpreted.</p>
     *
     * @param ex the {@link HttpMessageNotReadableException} thrown
     * @return a {@link TaskFlowResponse} with a message indicating malformed JSON and HTTP 400 status
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public TaskFlowResponse<Void> handleJsonParseError(HttpMessageNotReadableException ex) {
        return new TaskFlowResponse<>(CommonConstants.ERROR, String.valueOf(HttpStatus.BAD_REQUEST), "Malformed JSON request");
    }

    /**
     * Handles {@link EmptyDataException}, which is thrown when the incoming HTTP request body
     * cannot be parsed or read correctly, typically due to malformed JSON, invalid syntax, or incorrect data types.
     *
     * <p>This usually occurs when the client sends an invalid or incomplete JSON payload that cannot be
     * deserialized into the expected Java object.</p>
     *
     * <p>Returns an HTTP 400 (Bad Request) response with a descriptive error message indicating
     * that the request body was malformed.</p>
     *
     * @param ex the {@link EmptyDataException} thrown when the request body is invalid
     * @return a {@link TaskFlowResponse} indicating a bad request with details about the malformed JSON
     */
    @ExceptionHandler(EmptyDataException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public TaskFlowResponse<Void> handleEmptyDataException(EmptyDataException ex) {
        return new TaskFlowResponse<>(CommonConstants.ERROR, String.valueOf(HttpStatus.BAD_REQUEST), ex.getTitle() + "/n" + ex.getDetail());
    }
}
