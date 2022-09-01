package org.example.mapper;

import org.example.commons.MapperSingleton;
import org.example.dto.response.PersonaResponse;
import org.example.entity.Persona;
import org.modelmapper.ModelMapper;

public class PersonaMapperImpl {

    private static final ModelMapper personaMapper = MapperSingleton.getMapperInstance();

    public static PersonaResponse convertEntityToDto(Persona entity) {
        return personaMapper.map(entity, PersonaResponse.class);
    }
}
