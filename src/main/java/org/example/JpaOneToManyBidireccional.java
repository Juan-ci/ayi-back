package org.example;

import jakarta.persistence.EntityManager;
import org.example.configuration.JpaUtilDb;
import org.example.dto.request.AddressDto;
import org.example.dto.request.ClienteRequest;
import org.example.entity.Cliente;
import org.example.entity.Factura;

import java.util.ArrayList;
import java.util.List;

public class JpaOneToManyBidireccional {
    public static void main(String[] args) {
        EntityManager em = JpaUtilDb.getEntityManager();

        try {

            em.getTransaction().begin();

            List<Factura> listFactura = new ArrayList<>();

            Cliente cliente = Cliente.builder()
                    .nombre("Jorge")
                    .apellido("Borges")
                    .formaPago("PayPal")
                    .build();

            Factura f1 = new Factura("compras de supermercado", 5000L);
            f1.setCliente(cliente);
            Factura f2 = new Factura("compras de tecnologia", 7000L);
            f2.setCliente(cliente);

            listFactura.add(f1);
            listFactura.add(f2);

            cliente.setFacturas(listFactura);

            em.persist(cliente);

            System.out.println(cliente);

            List<Factura> listFactura2 = new ArrayList<>();


            Cliente cliente2 = Cliente.builder()
                    .nombre("Alvaro")
                    .apellido("Perez")
                    .formaPago("Efectivo")
                    .build();

            Factura f3 = new Factura("compras de supermercado y frutas", 5000L);
            f3.setCliente(cliente2);
            Factura f4 = new Factura("compras de tecnologia y muebles", 7000L);
            f4.setCliente(cliente2);

            listFactura2.add(f3);
            listFactura2.add(f4);

            cliente2.setFacturas(listFactura2);

            em.persist(cliente2);
            System.out.println(cliente2);

            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
}
