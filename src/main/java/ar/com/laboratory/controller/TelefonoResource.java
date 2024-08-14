package ar.com.laboratory.controller;


import ar.com.laboratory.config.exception.MaxRetriesException;
import ar.com.laboratory.domain.model.TelefonoResponse;
import ar.com.laboratory.service.TelefonoInfoService;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Tag(name = "Telefonos Resource", description = "Telefonos Resource")
@Path("/api/v1/telefonos")
public class TelefonoResource {

    @Inject
    TelefonoInfoService telefonoInfoService;

    @GET
    @Path("/info/{id}")
    @Produces(MediaType.APPLICATION_JSON)

    public Response obtenerTelefono(@PathParam("id") int id) throws MaxRetriesException {
        var telefonoResponse = telefonoInfoService.getTelefonoInfo(id);
        return Response.ok(telefonoResponse).build();
    }
}