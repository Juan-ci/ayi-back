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
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PersonaServiceImpl implements IPersonaService {

    private ConnectionDB conexionDB;

    private ConnectionDB conexionDBAux;

    private Connection conn;

    private Connection connAux;

    private PreparedStatement stmt;

    private PreparedStatement stmtAux;

    private ResultSet rs;

    private ResultSet rsAux;

    @Override
    public Integer createPersona(PersonaRequest request) {
        ResultSet rs = null;
        conexionDB = new ConnectionDB();
        conn = null;
        stmt = null;
        int indInsert = 0, risultato = 0;

        try {
            conn = conexionDB.getConnection();
            stmt = conn.prepareStatement(Constants.SQL_INSERT_PERSON, Statement.RETURN_GENERATED_KEYS);

            stmt.setString(1, request.getNombre());
            stmt.setString(2, request.getApellido());
            stmt.setString(3, request.getEdad());

            indInsert = stmt.executeUpdate();

            rs = stmt.getGeneratedKeys();
            if (rs.next()){
                risultato=rs.getInt(1);
            }
            System.out.println("ID RECUPERADO: " + risultato);
            if (indInsert == 0) {
                throw new RuntimeException();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (RuntimeException ex) {
            throw new RuntimeException("Error al insertar datos a la BD.");
        } finally {
            try {
                conexionDB.close(rs);
                conexionDB.close(stmt);
                conexionDB.close(conn);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        return indInsert;
    }

    @Override
    public List<PersonaResponse> readPersona() {
        conexionDB = new ConnectionDB();

        Persona persona = null;
        List<PersonaResponse> personas = new ArrayList<>();

        try {
            conn = conexionDB.getConnection();
            stmt = conn.prepareStatement(Constants.SQL_SELECT_PERSON);
            rs = stmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id_persona");
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                String edad = rs.getString("edad");

                persona = new Persona(id, nombre, apellido, edad);
                personas.add(PersonaMapperImpl.convertEntityToDto(persona));
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
        return personas;
    }

    @Override
    public Integer updatePersona(Integer id, PersonaRequest request) {
        PreparedStatement statementGet = null;
        conexionDB = new ConnectionDB();

        stmt = null;
        int indInsert = 0, getPersona = 0;

        try {
            getPersona = getPersonaById(id);

            if (getPersona == 0) {
                throw  new RuntimeException("Error al get");
            }
            conn = conexionDB.getConnection();
            stmt = conn.prepareStatement(Constants.SQL_UPDATE_PERSON);

            stmt.setString(1, request.getNombre());
            stmt.setString(2, request.getApellido());
            stmt.setString(3, request.getEdad());
            stmt.setInt(4, id);

            indInsert = stmt.executeUpdate();

            if (indInsert == 0) {
                throw new RuntimeException();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (RuntimeException ex) {
            throw new RuntimeException(ex.getMessage());
        } finally {
            try {
                if (stmt != null) {
                    conexionDB.close(stmt);
                    conexionDB.close(conn);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        return indInsert;
    }

    @Override
    public void deletePersona(Integer id) {
        PreparedStatement statementGet = null;
        conexionDB = new ConnectionDB();

        stmt = null;
        int indInsert = 0, getPersona = 0;

        try {
            getPersona = getPersonaById(id);

            if (getPersona == 0) {
                throw  new RuntimeException("Error al get");
            }
            conn = conexionDB.getConnection();
            stmt = conn.prepareStatement(Constants.SQL_DELETE_PERSON);
            stmt.setInt(1, id);

            indInsert = stmt.executeUpdate();

            if (indInsert == 0) {
                throw new RuntimeException();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (RuntimeException ex) {
            throw new RuntimeException(ex.getMessage());
        } finally {
            try {
                if (stmt != null) {
                    conexionDB.close(stmt);
                    conexionDB.close(conn);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        System.out.println("Id " + id + " borrado exitosamente.");
    }

    public Integer getPersonaById(Integer id) {
        conexionDBAux = new ConnectionDB();
        int response = 0;

        try {
            connAux = conexionDBAux.getConnection();
            stmtAux = connAux.prepareStatement(Constants.SQL_SELECT_PERSON_BY_ID);
            stmtAux.setInt(1, id);
            rsAux = stmtAux.executeQuery();

            if (!rsAux.next()) {
                throw new RuntimeException("Error al hacer el get.");
            } else {
                response = 1;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try{
                if (rsAux != null) {
                    conexionDBAux.close(rsAux);
                    conexionDBAux.close(stmtAux);
                    conexionDBAux.close(connAux);
                }
            } catch (SQLException ex) {
                throw new RuntimeException("Error al cerrar las conexiones");
            }
        }
        return response;
    }
}
