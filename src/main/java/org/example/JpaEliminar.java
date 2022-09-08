package org.example;

import jakarta.persistence.EntityManager;
import org.example.configuration.JpaUtilDb;
import org.example.entity.Cliente;

import java.util.Scanner;

public class JpaEliminar {

    public static void main(String[] args) {
        EntityManager em = JpaUtilDb.getEntityManager();
        Scanner scanner = new Scanner(System.in);
        System.out.println("INGRESA EL ID A BORRAR");
        Long id = scanner.nextLong();

        try {
            Cliente cliente = em.find(Cliente.class, id);

            em.getTransaction().begin();
            em.remove(cliente);
            em.getTransaction().commit();
        } catch(Exception ex) {
            em.getTransaction().rollback();

        } finally {
            em.close();
        }
    }
}
