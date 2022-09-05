package org.example.controller;

import org.example.dto.request.ClienteRequest;
import org.example.dto.response.ClienteResponse;
import org.example.service.IClienteService;
import org.example.service.impl.ClienteServiceImpl;

import java.util.List;

public class ClienteController {

    private IClienteService clienteService = new ClienteServiceImpl();

    public Integer createCliente(ClienteRequest request) {
        return clienteService.createCliente(request);
    }

    public List<ClienteResponse> getClientes() {
        return clienteService.readCliente();
    }

    public Integer updateCliente(Integer id, ClienteRequest request) {

        return clienteService.updateCliente(id, request);
    }

    public void deleteCliente(Integer id) {
        clienteService.deleteCliente(id);
    }
}
