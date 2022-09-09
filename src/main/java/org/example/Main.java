package org.example;

import jakarta.persistence.EntityManager;
import org.example.configuration.JpaUtilDb;
import org.example.controller.ClienteController;
import org.example.dto.request.ClienteRequest;
import org.example.dto.response.ClienteResponse;

import java.time.LocalDate;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        EntityManager em = JpaUtilDb.getEntityManager();

        ClienteController clienteController = new ClienteController(em);

        System.out.println("==== CREATE CLIENT ====");
        ClienteRequest request = ClienteRequest.builder()
                .nombre("Isabel")
                .apellido("Queen")
                .fechaCreacion(LocalDate.now())
                .formaPago("A piece of land")
                .build();

        clienteController.createCliente(request);

        System.out.println("==== GET ALL ====");
        List<ClienteResponse> responses = clienteController.getClientes();
        responses.forEach(System.out::println);

        System.out.println("==== UPDATE CLIENT ====");
        ClienteRequest updateClient = ClienteRequest.builder()
                .nombre("Pepito")
                .apellido("De los Palotes")
                .formaPago("Credito")
                .fechaCreacion(LocalDate.now())
                .build();
        clienteController.updateCliente(3L, updateClient);

        System.out.println("==== DELETE ====");
        clienteController.deleteCliente(9L);

        System.out.println("==== GET ALL AFTER DELETE ====");
        List<ClienteResponse> responsesAfterDelete = clienteController.getClientes();
        responsesAfterDelete.forEach(System.out::println);

        em.close();

    }
}