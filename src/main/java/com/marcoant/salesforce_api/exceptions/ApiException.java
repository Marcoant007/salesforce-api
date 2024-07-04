package com.marcoant.salesforce_api.exceptions;

import org.springframework.http.HttpStatus;

public class ApiException extends RuntimeException {

    private int httpStatus = HttpStatus.INTERNAL_SERVER_ERROR.value();

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

    public ApiException(Exception e, int httpStatus) {
        super(e);
        this.httpStatus = httpStatus;
    }

    public int getHttpStatus() {
        return this.httpStatus;
    }
}
