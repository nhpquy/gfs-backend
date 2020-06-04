package com.gfs.domain.exception;

import com.gfs.domain.model.ErrorResponseObject;
import com.gfs.domain.utils.GsonSingleton;
import org.springframework.http.HttpStatus;

public class ServiceException extends RuntimeException {

    private String error;
    private String description;
    private HttpStatus httpStatus;

    private static final long serialVersionUID = 1L;

    public ServiceException() {

    }


    public ServiceException(String error, String description, HttpStatus httpStatus) {
        super(description);
        this.error = error;
        this.description = description;
        this.httpStatus = httpStatus;
    }

    public ServiceException(String error, String description, HttpStatus httpStatus, boolean toHtmlResponse) {
        super(description);
        this.error = error;
        this.description = description;
        this.httpStatus = httpStatus;
    }

    @Deprecated
    public ServiceException(String error, HttpStatus httpStatus) {
        this.error = error;
        this.httpStatus = httpStatus;
        switch (this.error) {
            case "406001":
                this.description = "Currency code is not supported";
                break;
            case "406002":
                this.description = "Method not acceptable";
                break;
            case "406003":
                this.description = "Not enough amount";
                break;
            default:
                this.description = "";
                break;
        }
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public ErrorResponseObject generateResponse() {
        ErrorResponseObject response = new ErrorResponseObject();
        response.setError(this.error);
        response.setError_description(this.description);
        return response;
    }

    public String generateStringResponse() {
        return GsonSingleton.getInstance().toJson(generateResponse());
    }
}
