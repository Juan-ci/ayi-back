package org.example.controller;

import org.example.dto.request.EmpleadoRequest;
import org.example.dto.response.EmpleadoResponse;
import org.example.service.IEmpleadoService;
import org.example.service.impl.EmpleadoServiceImpl;

import java.util.List;

public class EmpleadoController {

    private IEmpleadoService empleadoService = new EmpleadoServiceImpl();

    public EmpleadoResponse createEmpleado(EmpleadoRequest request) {
       return empleadoService.createEmpleado(request);
    }

    public List<EmpleadoResponse> getEmpleado() {
        return empleadoService.readEmpleado();
    }

    public EmpleadoResponse updateEmpleado(Integer id, EmpleadoRequest request) {
        return empleadoService.updateEmpleado(id, request);
    }

    public void deleteEmpleado(Integer id) {
        empleadoService.deleteEmpleado(id);
    }
}
