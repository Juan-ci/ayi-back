package org.example.service.impl;

import org.example.configuration.ConnectionDB;
import org.example.constants.Constants;
import org.example.dto.request.ClienteRequest;
import org.example.dto.response.ClienteResponse;
import org.example.dto.response.EmpleadoResponse;
import org.example.entity.Cliente;
import org.example.entity.Empleado;
import org.example.mapper.ClienteMapperImpl;
import org.example.mapper.EmpleadoMapperImpl;
import org.example.service.IClienteService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ClienteServiceImpl implements IClienteService {

    private ConnectionDB conexionDB;

    private Connection conn;

    private PreparedStatement stmt;

    private ResultSet rs;

    @Override
    public ClienteResponse createCliente(ClienteRequest request) {
        //Creo la entidad
        Cliente entity = Cliente.builder().idCliente((int) (Math.random() * 100)).nombre("Cliente").apellido("Pepito").vip(request.getVip()).build();

        return ClienteMapperImpl.convertEntityToDto(entity);
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

                cliente = new Cliente(id, vip);
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
    public ClienteResponse updateCliente(Integer id, ClienteRequest request) {
        Cliente entity = Cliente.builder().idCliente(id).vip(request.getVip()).build();

        return ClienteMapperImpl.convertEntityToDto(entity);
    }

    @Override
    public void deleteCliente(Integer id) {
        System.out.println("Borrando datos de cliente con id: " + id);
    }
}
