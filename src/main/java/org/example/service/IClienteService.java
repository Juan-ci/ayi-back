package org.example.service;

import org.example.dto.request.ClienteRequest;
import org.example.dto.response.ClienteResponse;

import java.util.List;
import java.util.Optional;

public interface IClienteService {

    void createCliente(ClienteRequest request);

    List<ClienteResponse> readCliente();

    void updateCliente(Long id, ClienteRequest request);

    void deleteCliente(Long id);

    ClienteResponse getById(Long id);
}
