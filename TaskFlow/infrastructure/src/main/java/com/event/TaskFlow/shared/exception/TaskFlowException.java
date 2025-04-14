package com.event.TaskFlow.shared.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class TaskFlowException extends RuntimeException {

    public ProblemDetail toProblemDetail() {
        var pb = ProblemDetail.forStatus(HttpStatus.INTERNAL_SERVER_ERROR);

        pb.setTitle("TaskFlow Internal Server Error");

        return pb;
    }
}
