package org.example.service;

import org.example.dto.request.ClienteRequest;
import org.example.dto.response.ClienteResponse;

import java.util.List;

public interface IClienteService {

    Integer createCliente(ClienteRequest request);

    List<ClienteResponse> readCliente();

    Integer updateCliente(Integer id, ClienteRequest request);

    void deleteCliente(Integer id);
}
