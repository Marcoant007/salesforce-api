package com.marcoant.salesforce_api.exceptions;

public class ApiException extends RuntimeException {

    private int httpStatus;
    private String detailedMessage;

    public ApiException() {
        super();
    }

    public ApiException(String message) {
        super(message);
    }

    public ApiException(Exception e) {
        super(e);
    }

    public ApiException(String message, int httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }

    public ApiException(String message, String detailedMessage, int httpStatus) {
        super(message);
        this.detailedMessage = detailedMessage;
        this.httpStatus = httpStatus;
    }

    public ApiException(Exception e, int httpStatus) {
        super(e);
        this.httpStatus = httpStatus;
    }

    public int getHttpStatus() {
        return this.httpStatus;
    }

    public String getDetailedMessage() {
        return detailedMessage;
    }
}
