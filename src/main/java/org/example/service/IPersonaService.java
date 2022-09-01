package org.example.service;

import org.example.dto.request.PersonaRequest;
import org.example.dto.response.PersonaResponse;

import java.util.List;

public interface IPersonaService {

    PersonaResponse createPersona(PersonaRequest request);

    List<PersonaResponse> readPersona();

    PersonaResponse updatePersona(Integer id, PersonaRequest request);

    void deletePersona(Integer id);
}
