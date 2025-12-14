package com.mycompany.apprestaurante.Modelo.dao;

import com.mycompany.apprestaurante.Modelo.dto.orderTableDTO;
import com.mycompany.apprestaurante.Modelo.entities.Order;
import com.mycompany.apprestaurante.Modelo.entities.User;
import com.mycompany.apprestaurante.Modelo.interfaces.IOrderDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.mycompany.apprestaurante.Modelo.connectionBD.connection.getConnection;
import com.mycompany.apprestaurante.Modelo.entities.Dish;

public class OrderDAO implements IOrderDAO {

    @Override
    public void saveOrder(Order order) {

    }

    @Override
    public List<Order> listOrder() {
        List<Order> lista = new ArrayList<>();
        Connection con = getConnection();
        PreparedStatement ps;
        ResultSet rs;
        String sql = "SELECT * FROM orders";
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Order order = new Order();
                order.setId(rs.getInt("id"));
                order.setDateOrder(rs.getDate("dateOrder").toLocalDate());
                order.setIdTable(rs.getInt("idTable"));
                order.setIdWaiter(rs.getInt("idWaiter"));
                order.setTotal(rs.getDouble("total"));
                lista.add(order);
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

//    public String findWaiterNameById(int idWaiter) {
//        String sql = "SELECT name FROM waiter WHERE id_waiter = ?";
//        String waiterName = null;
//        Connection con = getConnection();
//        PreparedStatement ps;
//        try {
//            ps = con.prepareStatement(sql); 
//            ps.setInt(1, idWaiter);
//            ResultSet rs = ps.executeQuery();
//
//            if (rs.next()) {
//                waiterName = rs.getString("name");
//            }
//
//        } catch (SQLException e) {
//            System.out.println("Error al buscar el mesero: " + e.getMessage());
//        }
//
//        return waiterName;
//    }

    @Override
    public List<orderTableDTO> findByIdWaiter(int idWaiter) {
        List<orderTableDTO> list = new ArrayList<>();
        Connection con = getConnection();
        PreparedStatement ps;
        ResultSet rs;
        String sql = "SELECT orders.id , orders.total,orders.idTable,tables.state FROM restaurante.orders\n"
                + "inner join tables on orders.idTable = tables.id where idWaiter = ?;";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, idWaiter);
            rs = ps.executeQuery();
            while (rs.next()) {
                orderTableDTO orderTableDTO = new orderTableDTO();
                orderTableDTO.setTotal(rs.getInt("total"));
                orderTableDTO.setIdTable(rs.getInt("idTable"));
                orderTableDTO.setState(rs.getBoolean("state"));
                orderTableDTO.setIdOrder(rs.getInt("id"));
                list.add(orderTableDTO);
            }
            return list;
        } catch (SQLException e) {
            System.out.println("Error al listar pedidos: " + e.getMessage());
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println("Error al cerrar conexion: " + e.getMessage());
            }
        }
        return null;
    }

}
