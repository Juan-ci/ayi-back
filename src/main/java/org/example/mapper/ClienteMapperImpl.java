package org.example.mapper;

import org.example.commons.MapperSingleton;
import org.example.dto.request.ClienteRequest;
import org.example.dto.response.ClienteResponse;
import org.example.entity.Cliente;
import org.modelmapper.ModelMapper;

public class ClienteMapperImpl {

    private static final ModelMapper clienteMapper = MapperSingleton.getMapperInstance();

    public static ClienteResponse convertEntityToDto(Cliente entity) {
        return clienteMapper.map(entity, ClienteResponse.class);
    }

    public static Cliente convertEntityToDto(ClienteRequest request) {
        return clienteMapper.map(request, Cliente.class);
    }
}
