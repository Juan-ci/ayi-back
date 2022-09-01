package org.example.service;

import org.example.dto.request.EmpleadoRequest;
import org.example.dto.response.EmpleadoResponse;

import java.util.List;

public interface IEmpleadoService {

    EmpleadoResponse createEmpleado(EmpleadoRequest request);

    List<EmpleadoResponse> readEmpleado();

    EmpleadoResponse updateEmpleado(Integer id, EmpleadoRequest request);

    void deleteEmpleado(Integer id);
}
