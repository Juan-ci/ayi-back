package org.example.repository;

import org.example.entity.Cliente;

import java.util.List;

public interface IClienteRepository<T> {

    void save(Cliente cliente);

    Cliente getById(Long id);

    List<Cliente> getAll();

    void delete(Long id);
}
