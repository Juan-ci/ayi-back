package org.example.configuration;

import org.example.constants.Constants;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionDB {

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(Constants.JDBC_URL, Constants.JDBC_USER, Constants.JDBC_PASSWORD);
    }
    public void close(ResultSet rs) throws SQLException {
        rs.close();
    }

    public void close(Statement smtm) throws SQLException {
        smtm.close();
    }

    public void close(PreparedStatement stmt) throws SQLException {
        stmt.close();
    }

    public void close(Connection conn) throws SQLException{
        conn.close();
    }
}
