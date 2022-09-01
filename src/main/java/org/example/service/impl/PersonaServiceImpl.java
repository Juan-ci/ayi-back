package org.example.service.impl;

import org.example.configuration.ConnectionDB;
import org.example.dto.request.PersonaRequest;
import org.example.dto.response.PersonaResponse;
import org.example.entity.Persona;
import org.example.mapper.PersonaMapperImpl;
import org.example.service.IPersonaService;
import java.util.Arrays;
import java.util.List;

public class PersonaServiceImpl implements IPersonaService {

    private ConnectionDB conexionDB;

    @Override
    public PersonaResponse createPersona(PersonaRequest request) {
        Persona entity = Persona.builder()
                .idPersona((int) (Math.random() * 100))
                .nombre(request.getNombre())
                .apellido(request.getApellido())
                .edad(request.getEdad())
                .build();

        return PersonaMapperImpl.convertEntityToDto(entity);
    }

    @Override
    public List<PersonaResponse> readPersona() {
        PersonaResponse response1 = PersonaResponse.builder()
                .idPersona((int)(Math.random() * 100))
                .nombre("Jose")
                .apellido("Felix")
                .edad("40")
                .build();
        PersonaResponse response2 = PersonaResponse.builder()
                .idPersona((int)(Math.random() * 100))
                .nombre("Nicola")
                .apellido("Tesla")
                .edad("81")
                .build();

        List<PersonaResponse> listPersona = Arrays.asList(response1, response2);
        return listPersona;
    }

    @Override
    public PersonaResponse updatePersona(Integer id, PersonaRequest request) {
        Persona entity = Persona.builder()
                .idPersona(id)
                .nombre(request.getNombre())
                .apellido(request.getApellido())
                .edad(request.getEdad())
                .build();

        return PersonaMapperImpl.convertEntityToDto(entity);
    }

    @Override
    public void deletePersona(Integer id) {
        System.out.println("Borrando datos de persona con id: " + id);
    }
}
