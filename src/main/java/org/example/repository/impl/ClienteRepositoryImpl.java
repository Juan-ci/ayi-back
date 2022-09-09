package org.example.repository.impl;

import jakarta.persistence.EntityManager;
import org.example.entity.Cliente;
import org.example.repository.IClienteRepository;
import java.util.List;

public class ClienteRepositoryImpl implements IClienteRepository {

    private EntityManager em;

    public ClienteRepositoryImpl(EntityManager em) {
        this.em = em;
    }


    @Override
    public void save(Cliente cliente) {
        if (cliente.getIdCliente() != null && cliente.getIdCliente() > 0) {
            em.merge(cliente);
        } else {
            em.persist(cliente);
        }
    }



    @Override
    public Cliente getById(Long id) {
        return em.find(Cliente.class, id);
    }

    @Override
    public List<Cliente> getAll() {
        return em.createQuery("select c from Cliente c", Cliente.class).getResultList();
    }

    @Override
    public void delete(Long id) {
        Cliente cliente = getById(id);

        em.remove(cliente);
    }
}
