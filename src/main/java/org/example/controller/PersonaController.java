package org.example.controller;

import org.example.dto.request.PersonaRequest;
import org.example.dto.response.PersonaResponse;
import org.example.service.IPersonaService;
import org.example.service.impl.PersonaServiceImpl;

import java.util.List;

public class PersonaController {

    private IPersonaService personaService = new PersonaServiceImpl();

    public Integer createPersona(PersonaRequest request) {
        return personaService.createPersona(request);
    }

    public List<PersonaResponse> getPersona() {
        return personaService.readPersona();
    }

    public Integer updatePersona(Integer id, PersonaRequest request) {
        return personaService.updatePersona(id, request);
    }

    public void deletePersona(Integer id) {
        personaService.deletePersona(id);
    }
}
