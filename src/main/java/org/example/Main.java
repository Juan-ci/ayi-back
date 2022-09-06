package org.example;

import jakarta.persistence.EntityManager;
import org.example.configuration.JpaUtilDb;
import org.example.entity.Cliente;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        EntityManager em = JpaUtilDb.getEntityManager();

        List<Cliente> clientes = em.createQuery("select c from Cliente c", Cliente.class).getResultList();

        clientes.forEach(System.out::println);
        em.close();
    }
}