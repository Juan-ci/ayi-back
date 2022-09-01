package org.example.service.impl;

import org.example.dto.request.EmpleadoRequest;
import org.example.dto.response.EmpleadoResponse;
import org.example.entity.Empleado;
import org.example.mapper.EmpleadoMapperImpl;
import org.example.service.IEmpleadoService;

import java.util.Arrays;
import java.util.List;

public class EmpleadoServiceImpl implements IEmpleadoService {

    @Override
    public EmpleadoResponse createEmpleado(EmpleadoRequest request) {
        Empleado entity = Empleado.builder()
                .idEmpleado((int)(Math.random() * 100))
                .nombre(request.getNombre())
                .apellido(request.getApellido())
                .edad(request.getEdad())
                .sector(request.getSector())
                .antiguedad(request.getAntiguedad())
                .build();

        return EmpleadoMapperImpl.convertEntityToDto(entity);
    }

    @Override
    public List<EmpleadoResponse> readEmpleado() {
        EmpleadoResponse response1 = EmpleadoResponse.builder()
                .idEmpleado((int)(Math.random() * 100))
                .nombre("Oswald")
                .apellido("Osborn")
                .edad("56")
                .antiguedad("15 años")
                .sector("Deposito")
                .build();
        EmpleadoResponse response2 = EmpleadoResponse.builder()
                .idEmpleado((int)(Math.random() * 100))
                .nombre("Saint")
                .apellido("Jhon")
                .edad("54")
                .antiguedad("30 años")
                .sector("Administración")
                .build();

        List<EmpleadoResponse> responseList = Arrays.asList(response1, response2);
        return responseList;
    }

    @Override
    public EmpleadoResponse updateEmpleado(Integer id, EmpleadoRequest request) {
        Empleado entity = Empleado.builder()
                .idEmpleado(id)
                .nombre(request.getNombre())
                .apellido(request.getApellido())
                .edad(request.getEdad())
                .antiguedad(request.getAntiguedad())
                .sector(request.getSector())
                .build();

        return EmpleadoMapperImpl.convertEntityToDto(entity);
    }

    @Override
    public void deleteEmpleado(Integer id) {
        System.out.println("Borrando datos de empleado con id: " + id);
    }
}
