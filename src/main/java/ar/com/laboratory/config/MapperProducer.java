package ar.com.laboratory.config;

import ar.com.laboratory.domain.mappers.PersonaMapper;
import ar.com.laboratory.domain.mappers.PersonaResponseMapper;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

@ApplicationScoped
public class MapperProducer {

    @Produces
    public PersonaResponseMapper personaResponseMapper() {
        return PersonaResponseMapper.INSTANCE;
    }

    @Produces
    public PersonaMapper personaMapper() {
        return PersonaMapper.INSTANCE;
    }
}