package org.acme.config.exception.handler;

import org.acme.config.exception.CustomConflict2Exception;
import org.acme.config.exception.CustomConflictException;
import org.acme.domain.model.ErrorResponse;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.WebApplicationException;

@Provider
public class GenericExceptionMapper implements ExceptionMapper<Exception> {

    @Override
    public Response toResponse(Exception exception) {
        if (exception instanceof NotFoundException) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(new ErrorResponse(exception.getMessage(), Response.Status.NOT_FOUND.getStatusCode(), Response.Status.NOT_FOUND.name()))
                    .build();
        } else if (exception instanceof CustomConflictException || exception instanceof CustomConflict2Exception) {
            return Response.status(Response.Status.CONFLICT)
                    .entity(new ErrorResponse(exception.getMessage(), Response.Status.CONFLICT.getStatusCode(), Response.Status.CONFLICT.name()))
                    .build();
        } else if (exception instanceof WebApplicationException) {
            WebApplicationException webAppException = (WebApplicationException) exception;
            return Response.status(webAppException.getResponse().getStatus())
                    .entity(new ErrorResponse(exception.getMessage(), webAppException.getResponse().getStatus(), "WebApplicationException"))
                    .build();
        } else {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new ErrorResponse(exception.getMessage(), Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), Response.Status.INTERNAL_SERVER_ERROR.name()))
                    .build();
        }
    }
}
