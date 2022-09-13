package org.example.service.impl;

import jakarta.persistence.EntityManager;
import org.example.dto.request.ClienteRequest;
import org.example.dto.response.ClienteResponse;
import org.example.entity.Cliente;
import org.example.dto.request.mapper.ClienteMapperImpl;
import org.example.repository.IClienteRepository;
import org.example.repository.impl.ClienteRepositoryImpl;
import org.example.service.IClienteService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ClienteServiceImpl implements IClienteService {

    private EntityManager em;

    private IClienteRepository<Cliente> clientRepository;

    public ClienteServiceImpl(EntityManager em) {
        this.em = em;
        this.clientRepository = new ClienteRepositoryImpl(em);
    }

    @Override
    public void createCliente(ClienteRequest request) {
        try {
            Cliente entity = ClienteMapperImpl.convertEntityToDto(request);

            em.getTransaction().begin();
            em.persist(entity);

            em.getTransaction().commit();

            System.out.println(entity);
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<ClienteResponse> readCliente() {
        List<Cliente> clienteList = clientRepository.getAll();
        List<ClienteResponse> responses = new ArrayList<>();

        clienteList.forEach(entity -> {
            responses.add(ClienteMapperImpl.convertEntityToDto(entity));
        });

        return responses;
    }

    @Override
    public void updateCliente(Long id, ClienteRequest request) {
        try {
            Cliente entity = ClienteMapperImpl.convertEntityToDto(request);
            em.getTransaction().begin();
            entity.setIdCliente(id);
            clientRepository.save(entity);
            em.getTransaction().commit();

        } catch (Exception e) {
            em.getTransaction().rollback();
            System.out.println(e.getMessage());
        }
    }

    public ClienteResponse getById(Long id) {
        Optional<Cliente> cliente;

        cliente = Optional.ofNullable(clientRepository.getById(id));

        if (cliente.isPresent()) {
            return ClienteMapperImpl.convertEntityToDto(cliente.get());
        }

        return null;
    }

    @Override
    public void deleteCliente(Long id) {
        Optional<Cliente> cliente;

        cliente = Optional.ofNullable(clientRepository.getById(id));

        if (cliente.isPresent()) {
            try {
                em.getTransaction().begin();
                clientRepository.delete(id);
                em.getTransaction().commit();
            } catch (Exception e) {
                em.getTransaction().rollback();
                System.out.println(e.getMessage());
            }
        } else {
            System.out.println("El id no existe");
        }
    }
}
