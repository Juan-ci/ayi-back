package org.example.service.impl;

import org.example.configuration.ConnectionDB;
import org.example.constants.Constants;
import org.example.dto.request.EmpleadoRequest;
import org.example.dto.response.EmpleadoResponse;
import org.example.entity.Empleado;
import org.example.mapper.EmpleadoMapperImpl;
import org.example.service.IEmpleadoService;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmpleadoServiceImpl implements IEmpleadoService {

    private ConnectionDB conexionDB;

    private Connection conn;

    private PreparedStatement stmt;

    private ResultSet rs;

    @Override
    public EmpleadoResponse createEmpleado(EmpleadoRequest request) {
        Empleado entity = Empleado.builder()
                .idEmpleado((int) (Math.random() * 100))
                .nombre(request.getNombre())
                .apellido(request.getApellido())
                .edad(request.getEdad())
                .sector(request.getSector())
                .antiguedad(request.getAntiguedad())
                .build();

        return EmpleadoMapperImpl.convertEntityToDto(entity);
    }

    @Override
    public List<EmpleadoResponse> readEmpleado() {
        conexionDB = new ConnectionDB();

        Empleado empleado = null;
        List<EmpleadoResponse> empleadoResponseList = new ArrayList<>();

        try {
            conn = conexionDB.getConnection();
            stmt = conn.prepareStatement(Constants.SQL_SELECT_EMPLEADO);
            rs = stmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id_empleado");
                String sector = rs.getString("sector");
                String antiguedad = rs.getString("antiguedad");

                empleado = new Empleado(id, sector, antiguedad);
                empleadoResponseList.add(EmpleadoMapperImpl.convertEntityToDto(empleado));
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
        return empleadoResponseList;
    }

    @Override
    public EmpleadoResponse updateEmpleado(Integer id, EmpleadoRequest request) {
        Empleado entity = Empleado.builder()
                .idEmpleado(id)
                .nombre(request.getNombre())
                .apellido(request.getApellido())
                .edad(request.getEdad())
                .antiguedad(request.getAntiguedad())
                .sector(request.getSector())
                .build();

        return EmpleadoMapperImpl.convertEntityToDto(entity);
    }

    @Override
    public void deleteEmpleado(Integer id) {
        System.out.println("Borrando datos de empleado con id: " + id);
    }
}
