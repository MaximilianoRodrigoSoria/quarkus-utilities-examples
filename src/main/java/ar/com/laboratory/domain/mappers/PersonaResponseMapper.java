package ar.com.laboratory.domain.mappers;

import ar.com.laboratory.domain.dto.PersonaResponse;
import ar.com.laboratory.domain.entity.Persona;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import javax.enterprise.context.ApplicationScoped;

@Mapper
@ApplicationScoped
public interface PersonaResponseMapper {
    PersonaResponseMapper INSTANCE = Mappers.getMapper(PersonaResponseMapper.class);

    PersonaResponse toResponse(Persona persona);
}
