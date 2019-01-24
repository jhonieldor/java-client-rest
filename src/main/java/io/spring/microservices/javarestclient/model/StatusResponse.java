package io.spring.microservices.javarestclient.model;

import java.io.Serializable;

public class StatusResponse implements Serializable {

    private Integer code;

    private String message;

    private String content;

    private String logError;


    public StatusResponse(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public StatusResponse(Integer code, String content, String message) {
        this.code = code;
        this.content = content;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getLogError() {
        return logError;
    }

    public void setLogError(String logError) {
        this.logError = logError;
    }

    @Override
    public String toString() {
        return "StatusResponse{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", content='" + content + '\'' +
                ", logError='" + logError + '\'' +
                '}';
    }
}