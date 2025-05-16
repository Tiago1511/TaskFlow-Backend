package com.event.TaskFlow.shared.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class BadRequestException extends TaskFlowException {

    private String detail;
    private String title;

    public BadRequestException(String title,String detail) {
        this.detail = detail;
        this.title = title;
    }

    @Override
    public ProblemDetail toProblemDetail() {
        var pb = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);

        pb.setTitle(title);
        pb.setDetail(detail);

        return pb;
    }

    public String getTitle() {
        return title;
    }

    public String getDetail() {
        return detail;
    }
}
