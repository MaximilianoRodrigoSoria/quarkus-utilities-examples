package ar.com.laboratory.service;

import ar.com.laboratory.client.TelefonoClient;
import ar.com.laboratory.domain.model.TelefonoResponse;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class TelefonoInfoService {

    @Inject
    @RestClient
    TelefonoClient telefonoClient;

    public TelefonoResponse getTelefonoInfo(int id) {
        return telefonoClient.getTelefonoInfo(id);
    }
}