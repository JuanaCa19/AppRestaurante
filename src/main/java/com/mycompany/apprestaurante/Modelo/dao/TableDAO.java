package com.mycompany.apprestaurante.Modelo.dao;

import com.mycompany.apprestaurante.Modelo.connectionBD.connection;
import com.mycompany.apprestaurante.Modelo.entities.Table;
import com.mycompany.apprestaurante.Modelo.interfaces.ITableDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;

import java.util.List;

public class TableDAO implements ITableDAO {

    private connection conexion;

    public TableDAO() {
        conexion = new connection();
    }

    @Override
    public void saveTable(Table table) {
//        Connection con = conexion. getConnection();
//
//        PreparedStatement ps;
//        String sql = "INSERT INTO  tables(modelo, talla,color, Stock,precio)values(?,?,?,?,?)";
//        try {
//            ps = con.prepareStatement(sql);
//            ps.setString(1, product.getModelo());
//            ps.setInt(2, product.getTalla());
//            ps.setString(3, product.getColor());
//            ps.setInt(4, product.getStock());
//            ps.setDouble(5, product.getPrecio());
//            ps.execute();
//            return true;
//        } catch (Exception e) {
//            System.out.println("Error al ingresar documento" + e.getMessage());
//        } finally {
//            try {
//                con.close();
//            } catch (Exception e) {
//                System.out.println("Error al cerrar la conexion" + e.getMessage());
//            }
//        }
//        return false;
    }

    @Override
    public List<Table> listTable() {
        return List.of();
    }
}
