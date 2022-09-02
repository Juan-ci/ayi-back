package org.example.service;

import org.example.dto.request.PersonaRequest;
import org.example.dto.response.PersonaResponse;

import java.util.List;

public interface IPersonaService {

    Integer createPersona(PersonaRequest request);

    List<PersonaResponse> readPersona();

    Integer updatePersona(Integer id, PersonaRequest request);

    void deletePersona(Integer id);
}
