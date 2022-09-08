package org.example.service;

import jakarta.persistence.EntityManager;
import org.example.configuration.JpaUtilDb;
import org.example.entity.Cliente;

import javax.swing.*;
import java.time.LocalDate;
import java.util.Scanner;

public class JpaUpdate {
    public static void main(String[] args) {
        EntityManager em = JpaUtilDb.getEntityManager();

        try {
            Long id = Long.valueOf(JOptionPane.showInputDialog("Ingrese el id a modificar"));
            Cliente cliente = em.find(Cliente.class, id);

            if (cliente == null) {
                System.out.println("Objeto cliente nulo.");
            } else {
                String nombre = JOptionPane.showInputDialog("Ingrese el nombre");
                String apellido = JOptionPane.showInputDialog("Ingrese el apellido");
                String formaPago = JOptionPane.showInputDialog("Ingrese la forma de pago");

                em.getTransaction().begin();

                cliente.setNombre(nombre);
                cliente.setApellido(apellido);
                cliente.setFormaPago(formaPago);
                cliente.setFechaCreacion(LocalDate.now());
                em.merge(cliente);

                System.out.println("Cliente actualizado: " + cliente.toString());
                em.getTransaction().commit();
            }
        } catch(Exception ex) {
            em.getTransaction().rollback();

        } finally {
            em.close();
        }
    }
}
