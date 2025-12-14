package com.mycompany.apprestaurante.Modelo.dao;

import com.mycompany.apprestaurante.Modelo.entities.Dish;
import com.mycompany.apprestaurante.Modelo.entities.OrderDish;
import com.mycompany.apprestaurante.Modelo.entities.Waiter;
import com.mycompany.apprestaurante.Modelo.interfaces.IOrderDishDAO;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static com.mycompany.apprestaurante.Modelo.connectionBD.connection.getConnection;

public class OrderDishDAO implements IOrderDishDAO {
    @Override
    public void saveOrderDish(OrderDish orderDish) {
        Connection con = getConnection();
        PreparedStatement ps;
        ResultSet rs;
        String sql = "Insert Into order_dish (idOrder,idDish) VALUES (?,?)";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1,orderDish.getIdOrder());
            ps.setInt(2,orderDish.getIdDish());
            ps.execute();

        } catch (Exception e) {
            System.out.println("Error al insertar pedido plato" + e.getMessage());
        } finally {
            try {
                con.close();
            } catch (Exception e) {
                System.out.println("Error al cerrar la conexion" + e.getMessage());
            }
        }
    }

    @Override
    public List<OrderDish> listOrderDish() {
        return List.of();
    }

    @Override
    public List<Dish> findByIdOrder(int idOrder) {
        List<Dish> list = new ArrayList<>();
        Connection con = getConnection();
        PreparedStatement ps;
        ResultSet rs;
        String sql = "SELECT dishes.name FROM restaurante.order_dish\n" +
                "inner join dishes on order_dish.idDish = dishes.id where idOrder = ?;";
        try{
            ps = con.prepareStatement(sql);
            ps.setInt(1,idOrder);
            rs = ps.executeQuery();
            while(rs.next()){
                Dish dish = new Dish();
                dish.setNombre(rs.getString("name"));
                list.add(dish);
            }
            return list;
        }catch(SQLException e){
            System.out.println("Error al listar platos: " + e.getMessage());
        }finally{
            try{
                con.close();
            }catch(SQLException e){
                System.out.println("Error al cerrar conexion: " + e.getMessage());
            }
        }
        return null;
    }
}
