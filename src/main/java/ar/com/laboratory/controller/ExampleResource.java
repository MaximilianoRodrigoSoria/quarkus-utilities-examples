package ar.com.laboratory.controller;


import ar.com.laboratory.config.exception.CustomConflictException;
import ar.com.laboratory.domain.dto.PersonaDTO;
import ar.com.laboratory.domain.dto.PersonaResponse;
import ar.com.laboratory.domain.model.FullResponse;
import ar.com.laboratory.service.AggregatorService;
import ar.com.laboratory.service.PersonaService;
import ar.com.laboratory.util.AES256EncrypterUtil;
import ar.com.laboratory.util.JsonHandler;
import ar.com.laboratory.util.XmlHandler;
import ar.com.laboratory.util.annotations.CommonLogging;
import ar.com.laboratory.util.annotations.EncryptFields;
import io.opentelemetry.api.GlobalOpenTelemetry;
import io.opentelemetry.api.trace.Span;
import io.opentelemetry.api.trace.Tracer;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.logging.Logger;

@Tag(name = "Example Resource", description = "Example Resource")
@Path("/api/v1/example")
public class ExampleResource {

    private final Tracer tracer = GlobalOpenTelemetry.getTracer("Example Resource");


    @Inject
    JsonHandler jsonHandler;

    @Inject
    XmlHandler xmlHandler;

    @Inject
    PersonaService personaService;

    @Inject
    AggregatorService aggregatorService;

    @Inject
    AES256EncrypterUtil aes256EncrypterUtil;

    @ConfigProperty(name = "greeting")
    private String greeting;

    private static final Logger LOGGER = Logger.getLogger(ExampleResource.class.getName());

    @Operation(summary = "Obtener JSON de Persona", description = "Retorna los datos de una persona en formato JSON")
    @APIResponse(
            responseCode = "200",
            description = "Persona encontrada",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = PersonaDTO.class))
    )
    @GET
    @Path("/json")
    @Produces(MediaType.APPLICATION_JSON)
    public String json() throws Exception {


        Span span = tracer.spanBuilder("json").startSpan();

        PersonaDTO persona = getPersona("Juan", "Perez", 30);
        String json = jsonHandler.toJson(persona);

        var encrypterNombre = aes256EncrypterUtil.encrypt(persona.getNombre());
        LOGGER.info("Nombre encryptado:" + encrypterNombre);
        var desEncrypterNombre = aes256EncrypterUtil.decrypt(encrypterNombre);
        LOGGER.info("Nombre desencryptado: "+desEncrypterNombre);
        LOGGER.info("Persona to JSON: " + json);
        return json;
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/custom/{name}")
    public String customHello(@PathParam("name") String name) {
        return greeting + " " + name + " how are you doing?";
    }

    @CommonLogging
    public PersonaDTO getPersona(String nombre, String apellido, Integer edad) {
        //retraso de 30 segundos
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new PersonaDTO(nombre, apellido, edad);
    }


    @Operation(summary = "Obtener XML de Persona", description = "Retorna los datos de una persona en formato XML")
    @APIResponse(
            responseCode = "200",
            description = "Persona encontrada",
            content = @Content(mediaType = "application/xml", schema = @Schema(implementation = PersonaDTO.class))
    )
    @GET
    @Produces(MediaType.APPLICATION_XML)
    @Path("/xml")
    public String getXml() throws Exception {
        PersonaDTO personaDTO = new PersonaDTO("Juan", "Perez", 30);
        String xml = xmlHandler.toXml(personaDTO);
        LOGGER.info("Persona to XML: " + xml);
        return xml;
    }

    @Operation(summary = "Generar excepción", description = "Genera una excepción de conflicto personalizado")
    @APIResponse(
            responseCode = "409",
            description = "Conflicto encontrado",
            content = @Content(mediaType = "text/plain")
    )
    @GET
    @Path("/exception")
    @Produces(MediaType.TEXT_PLAIN)
    @CommonLogging
    public String exception() {
        if (true) {
            throw new CustomConflictException("Este es un mensaje de error personalizado 2");
        }
        return "Hello, World!";
    }

    @Operation(summary = "Obtener Persona por ID", description = "Retorna los datos de una persona basado en el ID proporcionado")
    @APIResponse(
            responseCode = "200",
            description = "Persona encontrada",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = PersonaResponse.class))
    )
    @GET
    @Path("/persona/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPersona(@PathParam("id") Long id) throws Exception {
            PersonaResponse persona = personaService.read(id);
            return Response.ok(persona).build();

    }


    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    @CommonLogging
    public Response fullResponse() {
        FullResponse fullResponse = aggregatorService.aggregateResponses().join();
        return Response.ok(fullResponse).build();
    }

    @POST
    @Path("/persona")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @CommonLogging
    @EncryptFields
    public Response createPersona(PersonaDTO personaDTO) {

        return Response.ok(personaDTO).build();
    }
}