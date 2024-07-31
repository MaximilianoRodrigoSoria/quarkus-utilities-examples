package ar.com.laboratory.config.exception.handler;

import ar.com.laboratory.config.exception.CustomConflictException;
import ar.com.laboratory.config.exception.IncompleteFlowException;
import ar.com.laboratory.config.exception.PersonaNotFoundException;
import ar.com.laboratory.domain.model.ErrorResponse;
import ar.com.laboratory.domain.model.ErrorsResponse;

import javax.validation.ConstraintViolationException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.stream.Collectors;

@Provider
public class GenericExceptionMapper implements ExceptionMapper<Exception> {

    @Override
    public Response toResponse(Exception exception) {
        if (exception instanceof javax.ws.rs.NotFoundException || exception instanceof PersonaNotFoundException) {
            return buildResponse(exception.getMessage(), Response.Status.NOT_FOUND);
        } else if (exception instanceof CustomConflictException || exception instanceof IncompleteFlowException) {
            return buildResponse(exception.getMessage(), Response.Status.CONFLICT);
        } else if (exception instanceof WebApplicationException) {
            WebApplicationException webAppException = (WebApplicationException) exception;
            return buildResponse(exception.getMessage(), Response.Status.fromStatusCode(webAppException.getResponse().getStatus()));
        } else if (exception instanceof ConstraintViolationException) {
            return handleConstraintViolationException((ConstraintViolationException) exception);
        } else {
            return buildResponse(exception.getMessage(), Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    private Response buildResponse(String message, Response.Status status) {
        ErrorResponse errorResponse = ErrorResponse.builder().message(  message)
                .code(String.valueOf(status.getStatusCode()))
                .status(status.name())
                .build();
        return Response.status(status).entity(errorResponse).build();
    }

    private Response handleConstraintViolationException(ConstraintViolationException exception) {
        ErrorsResponse errorsResponse = ErrorsResponse.builder()
                .errors(exception.getConstraintViolations().stream()
                        .map(violation -> violation.getMessage())
                        .collect(Collectors.toList()))
                .status(Response.Status.BAD_REQUEST.name())
                .code(String.valueOf(Response.Status.BAD_REQUEST.getStatusCode()))
                .build();
        return Response.status(Response.Status.BAD_REQUEST).entity(errorsResponse).build();
    }
}