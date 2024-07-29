package ar.com.laboratory.controller;


import ar.com.laboratory.domain.dto.PersonaDTO;
import ar.com.laboratory.service.PersonaService;
import ar.com.laboratory.util.annotations.CommonLogging;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Tag(name = "Persona Resource", description = "Persona Resource")
@Path("/api/v1/personas")
public class PersonaResource {
    @Inject
    PersonaService personaService;

    @Operation(summary = "Obtener JSON de Persona", description = "Retorna los datos de una persona en formato JSON")
    @APIResponse(
            responseCode = "200",
            description = "Persona encontrada",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = PersonaDTO.class))
    )
    @GET
    @Path("/persona/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("id") Long id) {
        var persona = personaService.read(id);
        return Response.ok(persona).build();
    }

    @Operation(summary = "Obtener JSON de todas las Personas", description = "Retorna los datos de todas las personas en formato JSON")
    @APIResponse(
            responseCode = "200",
            description = "Personas encontradas",
            content = @Content(mediaType = "application/json")
    )
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() throws Exception {
        var personas = personaService.readAll();
        return Response.ok(personas).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @CommonLogging
    public Response create(PersonaDTO personaDTO) {
        var persona = personaService.created(personaDTO);
        return Response.status(Response.Status.CREATED).entity(persona).build();
    }

    @PUT
    @Path("/persona/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON) // Ensure this matches the client's Content-Type
    public Response update(@PathParam("id") Long id, PersonaDTO personaDTO) {
        var persona = personaService.update(id, personaDTO);
        return Response.status(Response.Status.OK).entity(persona).build();
    }

    @DELETE
    @Path("/persona/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("id") Long id) {
        personaService.delete(id);
        return Response.status(Response.Status.NO_CONTENT).build();
    }
}
