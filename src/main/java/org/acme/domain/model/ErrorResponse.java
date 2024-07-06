package org.acme.domain.model;

public class ErrorResponse {
    private String errorMessage;
    private Integer errorCode;

    private String errorType;

    public ErrorResponse(String errorMessage, Integer errorCode, String errorType) {
        this.errorMessage = errorMessage;
        this.errorCode = errorCode;
        this.errorType = errorType;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorType() {
        return errorType;
    }

    public void setErrorType(String errorType) {
        this.errorType = errorType;
    }

    @Override
    public String toString() {
        return "ErrorResponse{" +
                "errorMessage='" + errorMessage + '\'' +
                ", errorCode=" + errorCode +
                ", errorType='" + errorType + '\'' +
                '}';
    }
}
