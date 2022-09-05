package org.example.service.impl;

import org.example.configuration.ConnectionDB;
import org.example.constants.Constants;
import org.example.dto.request.ClienteRequest;
import org.example.dto.response.ClienteResponse;
import org.example.entity.Cliente;
import org.example.mapper.ClienteMapperImpl;
import org.example.service.IClienteService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ClienteServiceImpl implements IClienteService {

    private ConnectionDB conexionDB;

    private ConnectionDB conexionDBAux;

    private Connection conn;

    private Connection connAux;

    private PreparedStatement stmt;

    private PreparedStatement stmtAux;

    private ResultSet rs;

    private ResultSet rsAux;

    @Override
    public Integer createCliente(ClienteRequest request) {
        ResultSet rs = null;
        conexionDB = new ConnectionDB();
        conn = null;
        stmt = null;
        int indInsert = 0, idGenerated = 0;

        try {
            conn = conexionDB.getConnection();
            stmt = conn.prepareStatement(Constants.SQL_INSERT_CLIENTE, Statement.RETURN_GENERATED_KEYS);

            stmt.setString(1, request.getVip());

            indInsert = stmt.executeUpdate();

            rs = stmt.getGeneratedKeys();

            if(rs.next()) {
                idGenerated = rs.getInt(1);
            }

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

        return 0;
    }

    @Override
    public List<ClienteResponse> readCliente() {
        conexionDB = new ConnectionDB();

        Cliente cliente = null;
        List<ClienteResponse> listClients = new ArrayList<>();

        try {
            conn = conexionDB.getConnection();
            stmt = conn.prepareStatement(Constants.SQL_SELECT_CLIENTE);
            rs = stmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id_cliente");
                String vip = rs.getString("vip");

//                cliente = new Cliente(id, vip);
                listClients.add(ClienteMapperImpl.convertEntityToDto(cliente));
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

        return listClients;
    }

    @Override
    public Integer updateCliente(Integer id, ClienteRequest request) {
        Cliente entity = Cliente.builder().idCliente(id).vip(request.getVip()).build();

        return 0;
    }

    @Override
    public void deleteCliente(Integer id) {
        System.out.println("Borrando datos de cliente con id: " + id);
    }
}
