package org.example;

import org.example.controller.ClienteController;
import org.example.controller.EmpleadoController;
import org.example.controller.PersonaController;
import org.example.dto.request.ClienteRequest;
import org.example.dto.request.EmpleadoRequest;
import org.example.dto.request.PersonaRequest;
import org.example.dto.response.ClienteResponse;
import org.example.dto.response.EmpleadoResponse;
import org.example.dto.response.PersonaResponse;
import org.example.entity.Cliente;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        PersonaController personaController = new PersonaController();

        PersonaRequest request = PersonaRequest.builder()
                .nombre("Juanito")
                .apellido("Cebal")
                .edad("25")
                .build();

        System.out.println("<--- PERSONA --->");

        PersonaResponse personaCreated = personaController.createPersona(request);
        System.out.println("PERSONA CREADA: " + personaCreated.toString());

        List<PersonaResponse> listPersonas = personaController.getPersona();
        System.out.println("LISTA PERSONA: " + listPersonas.toString());

        PersonaRequest updatePersona = PersonaRequest.builder()
                .nombre("Alphie")
                .apellido("Salomons")
                .edad("45")
                .build();
        PersonaResponse personaUpdated = personaController.updatePersona(1, updatePersona);
        System.out.println("PERSONA UPDATED: " + personaUpdated.toString());

        personaController.deletePersona(1);

        /*  <--- CLIENTE --->   */

        ClienteController clienteController = new ClienteController();

        ClienteRequest clienteRequest = new ClienteRequest();
        clienteRequest.setVip("VIP");

        System.out.println("<--- CLIENTE --->");

        ClienteResponse createdClient = clienteController.createCliente(clienteRequest);
        System.out.println("Cliente creado: " + createdClient.toString());

        List<ClienteResponse> listClient = clienteController.getClientes();
        System.out.println("LISTA CLIENTES: " + listClient.toString());

        ClienteRequest clienteRequestUpdated = new ClienteRequest();
        clienteRequestUpdated.setVip("NOT-VIP");

        ClienteResponse updatedClient = clienteController.updateCliente(1, clienteRequestUpdated);
        System.out.println("Cliente modificado: " + updatedClient.toString());

        clienteController.deleteCliente(1);

        /*  <-- EMPLEADO -->    */

        EmpleadoController empleadoController = new EmpleadoController();

        EmpleadoRequest request1 = EmpleadoRequest.builder()
                .nombre("Cosme")
                .apellido("Fulanito")
                .edad("32")
                .antiguedad("8 a??os")
                .sector("Almacen")
                .build();

        System.out.println("<--- EMPLEADO --->");

        EmpleadoResponse createdEmpleado = empleadoController.createEmpleado(request1);
        System.out.println("EMPLEADO CREATED: " + createdEmpleado.toString());

        List<EmpleadoResponse> listEmpleados = empleadoController.getEmpleado();
        System.out.println("LIST EMPLEADOS: " + listEmpleados.toString());

        EmpleadoRequest updateEmpleado = EmpleadoRequest.builder()
                .nombre("Carl")
                .apellido("Karlson")
                .edad("37")
                .antiguedad("10")
                .sector("Operario")
                .build();
        EmpleadoResponse empleadoUpdated = empleadoController.updateEmpleado(2, updateEmpleado);
        System.out.println("EMPLEADO UPDATED: " + empleadoUpdated.toString());

        empleadoController.deleteEmpleado(2);
    }
}