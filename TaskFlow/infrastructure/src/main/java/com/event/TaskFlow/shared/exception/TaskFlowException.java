package com.event.TaskFlow.shared.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class TaskFlowException extends RuntimeException {

    private HttpStatus httpStatus;

    public TaskFlowException(String s, HttpStatus httpStatus) {
        super(s);
        this.httpStatus = httpStatus == null ? HttpStatus.INTERNAL_SERVER_ERROR : httpStatus;
    }

    public TaskFlowException() {
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public ProblemDetail toProblemDetail() {
        var pb = ProblemDetail.forStatus(HttpStatus.INTERNAL_SERVER_ERROR);

        pb.setTitle("TaskFlow Internal Server Error");

        return pb;
    }
}
