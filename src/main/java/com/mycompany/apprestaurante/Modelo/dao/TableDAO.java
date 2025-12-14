package com.mycompany.apprestaurante.Modelo.dao;

import static com.mycompany.apprestaurante.Modelo.connectionBD.connection.getConnection;
import com.mycompany.apprestaurante.Modelo.entities.Table;
import com.mycompany.apprestaurante.Modelo.interfaces.ITableDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import java.util.List;

public class TableDAO implements ITableDAO {




    @Override
    public boolean saveTable(Table table) {
        Connection con = getConnection();

        PreparedStatement ps;
        String sql = "INSERT INTO tables(capacity, state)values(?,?)";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, table.getCapacity());
            ps.setBoolean(2, table.isState());
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.out.println("Error al ingresar mesa" + e.getMessage());
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println("Error al cerrar la conexion de mesa" + e.getMessage());
            }
        }
        return false;
    }

    @Override
    public List<Table> listTable() {
        List<Table> lista = new ArrayList<>();
        Connection con = getConnection();
        PreparedStatement ps;
        ResultSet rs;
        String sql = "SELECT * FROM tables";
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Table table = new Table();
                table.setId(rs.getInt("id"));
                table.setCapacity(rs.getInt("capacity"));
                table.setState(rs.getBoolean("state"));
                lista.add(table);
            }
            return lista;
        } catch ( SQLException e) {
            System.out.println("Error al listar tabla de mesas" + e.getMessage());
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println("Error al cerrar la conexion" + e.getMessage());
            }
        }
        return null;
    }

    @Override
    public void modifyTable(int idTable) {
        Connection con = getConnection();
        PreparedStatement ps;
        String sql = "UPDATE tables set state = ? WHERE id = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setBoolean(1, false);
            ps.setInt(2, idTable);
            ps.execute();
        } catch (SQLException e) {
            System.out.println("Error al ingresar mesa" + e.getMessage());
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println("Error al cerrar la conexion de mesa" + e.getMessage());
            }
        }
    }
}
