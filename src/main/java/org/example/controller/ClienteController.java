package org.example.controller;

import jakarta.persistence.EntityManager;
import org.example.dto.request.ClienteRequest;
import org.example.dto.response.ClienteResponse;
import org.example.service.IClienteService;
import org.example.service.impl.ClienteServiceImpl;
import java.util.List;

public class ClienteController {

    private EntityManager em;
    private IClienteService clienteService;

    public ClienteController(EntityManager em) {
        this.em = em;
        this.clienteService = new ClienteServiceImpl(em);
    }


    public void createCliente(ClienteRequest request) {
        clienteService.createCliente(request);
        System.out.println("CLIENTE CREADO CON EXITO.");
    }

    public List<ClienteResponse> getClientes() {
        return clienteService.readCliente();
    }

    public void updateCliente(Long id, ClienteRequest request) {
        clienteService.updateCliente(id, request);
        System.out.println("CLIENTE MODIFICADO CON EXITO");
    }

    public void deleteCliente(Long id) {
        clienteService.deleteCliente(id);
    }
}
