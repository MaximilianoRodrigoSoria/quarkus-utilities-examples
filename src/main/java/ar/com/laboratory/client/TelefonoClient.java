package ar.com.laboratory.client;

import ar.com.laboratory.domain.model.TelefonoResponse;
import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/telefonos/info")
@RegisterRestClient
public interface TelefonoClient {

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Retry(maxRetries = 3, delay = 1000) // Intenta 3 veces con un retraso de 1 segundo entre intentos
    TelefonoResponse getTelefonoInfo(@PathParam("id") int id);
}