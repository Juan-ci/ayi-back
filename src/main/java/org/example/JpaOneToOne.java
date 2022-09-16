package org.example;

import jakarta.persistence.EntityManager;
import org.example.configuration.JpaUtilDb;
import org.example.entity.Cliente;
import org.example.entity.ClienteDetalle;

public class JpaOneToOne {
    public static void main(String[] args) {
        EntityManager em = JpaUtilDb.getEntityManager();

        try {
            em.getTransaction().begin();

            Cliente cliente = Cliente.builder()
                    .nombre("Florinda")
                    .apellido("Mesa")
                    .formaPago("Mercado Pago")
                    .build();

            ClienteDetalle clienteDetalle = ClienteDetalle.builder()
                    .prime(true)
                    .puntosAcumulados(5_000L)
                    .build();

            clienteDetalle.setCliente(cliente);
            cliente.setClienteDetalle(clienteDetalle);

            em.persist(cliente);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
}
