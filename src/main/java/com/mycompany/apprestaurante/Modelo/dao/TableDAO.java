package com.mycompany.apprestaurante.Modelo.dao;

import com.mycompany.apprestaurante.Modelo.connectionBD.connection;
import com.mycompany.apprestaurante.Modelo.entities.Table;
import com.mycompany.apprestaurante.Modelo.interfaces.ITableDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import java.util.List;

public class TableDAO implements ITableDAO {

    private connection conexion;

    public TableDAO() {
        conexion = new connection();
    }

    @Override
    public boolean saveTable(Table table) {
        Connection con = conexion. getConnection();

        PreparedStatement ps;
        String sql = "INSERT INTO  tables(capacity, availade)values(?,?)";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, table.getCapacity());
            ps.setString(2, table.getAvailade());
            ps.execute();
            return true;
        } catch (Exception e) {
            System.out.println("Error al ingresar mesa" + e.getMessage());
        } finally {
            try {
                con.close();
            } catch (Exception e) {
                System.out.println("Error al cerrar la conexion de mesa" + e.getMessage());
            }
        }
        return false;
    }

    @Override
    public List<Table> listTable() {
        List<Table> lista = new ArrayList<>();
        Connection con = conexion.getConnection();
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
                table.setAvailade(rs.getString("availade"));
                lista.add(table);
            }
            return lista;
        } catch (Exception e) {
            System.out.println("Error al listar tabla" + e.getMessage());
        } finally {
            try {
                con.close();
            } catch (Exception e) {
                System.out.println("Error al cerrar la conexion" + e.getMessage());
            }
        }
        return null;
    }
}
