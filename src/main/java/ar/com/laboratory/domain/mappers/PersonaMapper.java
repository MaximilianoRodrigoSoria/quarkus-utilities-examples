package ar.com.laboratory.domain.mappers;

import ar.com.laboratory.domain.dto.PersonaDTO;
import ar.com.laboratory.domain.entity.Persona;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import javax.enterprise.context.ApplicationScoped;

@Mapper
@ApplicationScoped
public interface PersonaMapper {

    PersonaMapper INSTANCE = Mappers.getMapper(PersonaMapper.class);


    Persona toEntity(PersonaDTO personaDTO);
}
