package com.mycompany.apprestaurante.Modelo.dao;

import com.mycompany.apprestaurante.Modelo.connectionBD.connection;
import com.mycompany.apprestaurante.Modelo.entities.Dish;
import com.mycompany.apprestaurante.Modelo.entities.Table;
import com.mycompany.apprestaurante.Modelo.interfaces.IDishDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import java.util.List;

public class DishDAO implements IDishDAO {
     private connection conexion;

    public DishDAO() {
        conexion = new connection();
    }

    
    @Override
    public boolean saveDish(Dish dish) {
        Connection con = conexion. getConnection();

        PreparedStatement ps;
        String sql = "INSERT INTO  dishes(nombre, precio)values(?,?)";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, dish.getNombre());
            ps.setDouble(2, dish.getPrice());
            ps.execute();
            return true;
        } catch (Exception e) {
            System.out.println("Error al ingresar plato" + e.getMessage());
        } finally {
            try {
                con.close();
            } catch (Exception e) {
                System.out.println("Error al cerrar la conexion de plato" + e.getMessage());
            }
        }
        return false;
    }

    @Override
    public List<Dish> listDish() {
         List<Dish> lista = new ArrayList<>();
        Connection con = conexion.getConnection();
        PreparedStatement ps;
        ResultSet rs;
        String sql = "SELECT * FROM dishes";
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Dish dish = new Dish();
                dish.setId(rs.getInt("id"));
                dish.setNombre(rs.getString("nombre"));
                dish.setPrice(rs.getDouble("precio"));
                lista.add(dish);
            }
            return lista;
        } catch (Exception e) {
            System.out.println("Error al listar plato" + e.getMessage());
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
