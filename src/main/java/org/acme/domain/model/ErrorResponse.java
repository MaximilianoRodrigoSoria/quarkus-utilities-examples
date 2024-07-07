package org.acme.domain.model;


import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Schema(description = "Representa la devolucion de un error")
public class ErrorResponse {

    @Schema(description = "Es el mensaje de error", example = "Persona no encontrada")
    private String errorMessage;

    @Schema(description = "Es el codigo de error", example = "404")
    private Integer errorCode;

    @Schema(description = "Es el tipo de error", example = "NOT_FOUND")
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
