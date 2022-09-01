package org.example.mapper;

import org.example.commons.MapperSingleton;
import org.example.dto.response.EmpleadoResponse;
import org.example.entity.Empleado;
import org.modelmapper.ModelMapper;

public class EmpleadoMapperImpl {

    private static final ModelMapper empleadoMapper = MapperSingleton.getMapperInstance();

    public static EmpleadoResponse convertEntityToDto(Empleado entity) {
        return empleadoMapper.map(entity, EmpleadoResponse.class);
    }
}
