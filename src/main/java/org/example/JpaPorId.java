package org.example;

import jakarta.persistence.EntityManager;
import org.example.configuration.JpaUtilDb;
import org.example.entity.Cliente;

import java.util.Scanner;

public class JpaPorId {
    public static void main(String[] args) {
        EntityManager em = JpaUtilDb.getEntityManager();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese el id");
        Long id = scanner.nextLong();

        Cliente cliente = em.find(Cliente.class, id);
        System.out.println(cliente.toString());

        em.close();
    }
}
