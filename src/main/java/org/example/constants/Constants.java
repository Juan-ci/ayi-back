package org.example.constants;

public class Constants {
    /**
     * SQL querys person entity constant.
     */
    //    <---- PERSONA ---->
    public static final String SQL_SELECT_PERSON = "SELECT id_persona, nombre, apellido, edad FROM personas";
    public static final String SQL_SELECT_PERSON_BY_ID = "SELECT id_persona FROM personas WHERE id_persona = ?";
    public static final String SQL_INSERT_PERSON = "INSERT INTO personas(nombre, apellido, edad) VALUES(?, ?, ?)";
    public static final String SQL_UPDATE_PERSON = "UPDATE personas SET nombre = ?, apellido = ?, edad = ? WHERE id_persona = ?";
    public static final String SQL_DELETE_PERSON = "DELETE FROM personas WHERE id_persona = ?";

    //    <---- EMPLEADO ---->
    public static final String SQL_SELECT_EMPLEADO = "SELECT id_empleado, sector, antiguedad FROM empleados";
    public static final String SQL_INSERT_EMPLEADO = "INSERT INTO empleados(sector, antiguedad) VALUES(?, ?)";
    public static final String SQL_UPDATE_EMPLEADO = "UPDATE empleados SET sector = ?, antiguedad = ? WHERE id_empleado = ?";
    public static final String SQL_DELETE_EMPLEADO = "DELETE FROM empleados WHERE id_empleado = ?";

    //    <---- CLIENTE ---->
    public static final String SQL_SELECT_CLIENTE = "SELECT id_cliente, vip FROM clientes";
    public static final String SQL_INSERT_CLIENTE = "INSERT INTO clientes(vip) VALUES(?)";
    public static final String SQL_UPDATE_CLIENTE = "UPDATE clientes SET vip = ? WHERE id_cliente = ?";
    public static final String SQL_DELETE_CLIENTE = "DELETE FROM clientes WHERE id_cliente = ?";

    /**
     * Variables de conexion a la base de datos.     */

    public static final String JDBC_URL = "jdbc:mysql://localhost:3306/curso_ayi_db?useSSL=false&useTimezone=true&serverTimezone=UTC&allowPublicKeyRetrieval=true";
    public static final String JDBC_USER = "root";
    public static final String JDBC_PASSWORD = "root";

}
