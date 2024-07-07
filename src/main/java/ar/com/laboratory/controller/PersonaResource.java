package ar.com.laboratory.controller;


import ar.com.laboratory.domain.dto.PersonaDTO;
import ar.com.laboratory.service.PersonaService;
import ar.com.laboratory.util.JsonHandler;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static io.vertx.core.json.Json.mapper;

@Tag(name = "Persona Resource", description = "Persona Resource")
@Path("/api/v1/personas")
public class PersonaResource {

    @Inject
    PersonaService personaService;

    @Inject
    JsonHandler jsonHandler;

    @Operation(summary = "Obtener JSON de Persona", description = "Retorna los datos de una persona en formato JSON")
    @APIResponse(
            responseCode = "200",
            description = "Persona encontrada",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = PersonaDTO.class))
    )
    @GET
    @Path("/persona/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("id") Long id) throws Exception {
        var persona = personaService.read(id);
        String json = jsonHandler.toJson(persona);
        return Response.ok(json).build();
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
        String json = jsonHandler.toJson(personas);
        return Response.ok(json).build();
    }

@POST
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
 public Response create(PersonaDTO personaDTO) throws Exception {
     var persona = personaService.created(personaDTO);
    String json = jsonHandler.toJson(persona);
    return Response.status(Response.Status.CREATED).entity(json).build();
}

@PUT
@Path("/persona")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON) // Ensure this matches the client's Content-Type
public Response update(PersonaDTO personaDTO) throws Exception {
    var persona = personaService.update(personaDTO);
    String json = jsonHandler.toJson(persona);
    return Response.ok(json).build();
}

}
