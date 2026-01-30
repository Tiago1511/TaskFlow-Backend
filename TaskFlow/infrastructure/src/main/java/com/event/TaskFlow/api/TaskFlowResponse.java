package com.event.TaskFlow.api;

public class TaskFlowResponse<T>  {

    private String status;
    private String code;
    private String message;
    private T data;

    public TaskFlowResponse() {}

    public TaskFlowResponse(String status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }

    public TaskFlowResponse(String status, T data, String message, String code) {
        this.status = status;
        this.data = data;
        this.message = message;
        this.code = code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}