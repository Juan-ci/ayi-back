package org.example.service.impl;

import org.example.configuration.ConnectionDB;
import org.example.constants.Constants;
import org.example.dto.request.PersonaRequest;
import org.example.dto.response.PersonaResponse;
import org.example.entity.Persona;
import org.example.mapper.PersonaMapperImpl;
import org.example.service.IPersonaService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PersonaServiceImpl implements IPersonaService {

    private ConnectionDB conexionDB;

    @Override
    public PersonaResponse createPersona(PersonaRequest request) {
        Persona entity = Persona.builder()
                .idPersona((int) (Math.random() * 100))
                .nombre(request.getNombre())
                .apellido(request.getApellido())
                .edad(request.getEdad())
                .build();

        return PersonaMapperImpl.convertEntityToDto(entity);
    }

    @Override
    public List<PersonaResponse> readPersona() {
        /*PersonaResponse response1 = PersonaResponse.builder()
                .idPersona((int)(Math.random() * 100))
                .nombre("Jose")
                .apellido("Felix")
                .edad("40")
                .build();
        PersonaResponse response2 = PersonaResponse.builder()
                .idPersona((int)(Math.random() * 100))
                .nombre("Nicola")
                .apellido("Tesla")
                .edad("81")
                .build();

        List<PersonaResponse> listPersona = Arrays.asList(response1, response2);
        return listPersona;
        */
        conexionDB = new ConnectionDB();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Persona persona = null;
        List<Persona> personas = new ArrayList<>();

        try {
            conn = conexionDB.getConnection();
            stmt = conn.prepareStatement(Constants.SQL_SELECT_PERSON);
            rs = stmt.executeQuery();


            while (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                String edad = rs.getString("edad");
                String direccion = rs.getString("direccion");

                persona = new Persona(id, nombre, apellido, edad);
                personas.add(persona);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                conexionDB.close(rs);
                conexionDB.close(stmt);
                conexionDB.close(conn);
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
        }
//        return personas;

        return null;
    }

    @Override
    public PersonaResponse updatePersona(Integer id, PersonaRequest request) {
        Persona entity = Persona.builder()
                .idPersona(id)
                .nombre(request.getNombre())
                .apellido(request.getApellido())
                .edad(request.getEdad())
                .build();

        return PersonaMapperImpl.convertEntityToDto(entity);
    }

    @Override
    public void deletePersona(Integer id) {
        System.out.println("Borrando datos de persona con id: " + id);
    }
}
