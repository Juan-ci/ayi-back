package org.example;

import jakarta.persistence.EntityManager;
import org.example.configuration.JpaUtilDb;
import org.example.controller.ClienteController;
import org.example.dto.request.AddressDto;
import org.example.dto.request.ClienteRequest;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        EntityManager em = JpaUtilDb.getEntityManager();

        ClienteController clienteController = new ClienteController(em);

        AddressDto addressDto = AddressDto.builder()
                .calle("USA")
                .numero(123)
                .build();

        AddressDto addressDto2 = AddressDto.builder()
                .calle("Dessert")
                .numero(532)
                .build();

        ClienteRequest clienteRequest = ClienteRequest.builder()
                .nombre("Nikola")
                .apellido("Tesla")
                .formaPago("Gold")
                .direcciones(List.of(addressDto, addressDto2))
                .build();

        clienteController.createCliente(clienteRequest);

        em.close();

    }
}