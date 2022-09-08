package org.example;

import jakarta.persistence.EntityManager;
import org.example.configuration.JpaUtilDb;
import org.example.entity.Cliente;

import javax.swing.*;
import java.time.LocalDate;


public class JpaCrear {
    public static void main(String[] args) {
        EntityManager em = JpaUtilDb.getEntityManager();

        try {
            String nombre = JOptionPane.showInputDialog("Ingrese el nombre");
            String apellido = JOptionPane.showInputDialog("Ingrese el apellido");
            String formaPago = JOptionPane.showInputDialog("Ingrese el pago");

            Cliente cliente = Cliente.builder()
                    .nombre(nombre)
                    .apellido(apellido)
                    .formaPago(formaPago)
                    .fechaCreacion(LocalDate.now())
                    .build();

            em.getTransaction().begin();
            em.persist(cliente);
            System.out.println("Cliente guardado con éxito.");

            em.getTransaction().commit();
        } catch (Exception ex) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
}
