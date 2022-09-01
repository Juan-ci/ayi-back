package org.example.service.impl;

import org.example.dto.request.ClienteRequest;
import org.example.dto.response.ClienteResponse;
import org.example.entity.Cliente;
import org.example.mapper.ClienteMapperImpl;
import org.example.service.IClienteService;

import java.util.Arrays;
import java.util.List;

public class ClienteServiceImpl implements IClienteService {

    @Override
    public ClienteResponse createCliente(ClienteRequest request) {
        //Creo la entidad
        Cliente entity = Cliente.builder()
                .idCliente((int) (Math.random() * 100))
                .nombre("Cliente")
                .apellido("Pepito")
                .vip(request.getVip())
                .build();

        return ClienteMapperImpl.convertEntityToDto(entity);
    }

    @Override
    public List<ClienteResponse> readCliente() {
        ClienteResponse cliente1 = ClienteResponse.builder()
                .idCliente((int)(Math.random() * 100))
                .vip("NO-VIP")
                .build();

        ClienteResponse cliente2 = ClienteResponse.builder()
                .idCliente((int)(Math.random() * 100))
                .vip("VIP")
                .build();
        List<ClienteResponse> listClients = Arrays.asList(cliente1, cliente2);

        return listClients;
    }

    @Override
    public ClienteResponse updateCliente(Integer id, ClienteRequest request) {
        Cliente entity = Cliente.builder()
                .idCliente(id)
                .vip(request.getVip())
                .build();

        return ClienteMapperImpl.convertEntityToDto(entity);
    }

    @Override
    public void deleteCliente(Integer id) {
        System.out.println("Borrando datos de cliente con id: " + id);
    }
}
