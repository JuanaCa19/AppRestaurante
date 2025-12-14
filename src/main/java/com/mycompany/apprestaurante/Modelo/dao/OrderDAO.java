package com.mycompany.apprestaurante.Modelo.dao;

import com.mycompany.apprestaurante.Modelo.dto.orderTableDTO;
import com.mycompany.apprestaurante.Modelo.entities.Order;
import com.mycompany.apprestaurante.Modelo.entities.User;
import com.mycompany.apprestaurante.Modelo.interfaces.IOrderDAO;

import java.sql.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static com.mycompany.apprestaurante.Modelo.connectionBD.connection.getConnection;

import java.time.LocalDate;

public class OrderDAO implements IOrderDAO {
    
    @Override
    public int saveOrder(Order order) {
        Connection con = getConnection();
        PreparedStatement ps;
        ResultSet rs;
        String sql = "Insert Into orders (dateOrder,timeOrder,idTable,idWaiter,total,state) VALUES (?,?,?,?,?,?)";
        try {
            ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setDate(1, Date.valueOf(LocalDate.now()));
            ps.setTime(2, Time.valueOf(LocalTime.now()));
            ps.setInt(3,order.getIdTable());
            ps.setInt(4,order.getIdWaiter());
            ps.setDouble(5,order.getTotal());
            ps.setBoolean(6, true);
            ps.executeUpdate();
            rs = ps.getGeneratedKeys();
            if(rs.next()){
                int idOrder = rs.getInt(1);
                return idOrder;
            }
        } catch (Exception e) {
            System.out.println("Error al insertar pedido" + e.getMessage());
        } finally {
            try {
                con.close();
            } catch (Exception e) {
                System.out.println("Error al cerrar la conexion" + e.getMessage());
            }
        }
       return -1;
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


    @Override
    public List<Order> findByIdWaiter(int idWaiter) {
        List<Order> list = new ArrayList<>();
        Connection con = getConnection();
        PreparedStatement ps;
        ResultSet rs;
        String sql = "SELECT * FROM restaurante.orders WHERE idWaiter = ?"; 
        try{
            ps = con.prepareStatement(sql);
            ps.setInt(1,idWaiter);
            rs = ps.executeQuery();
            while(rs.next()){
                Order order = new Order();
                order.setTotal(rs.getInt("total"));
                order.setIdTable(rs.getInt("idTable"));
                order.setState(rs.getBoolean("state"));
                order.setId(rs.getInt("id"));
                list.add(order);
            }
            return list;
        }catch(SQLException e){
            System.out.println("Error al listar pedidos: " + e.getMessage());
        }finally{
            try{
                con.close();
            }catch(SQLException e){
                System.out.println("Error al cerrar conexion: " + e.getMessage());
            }
        }
        return null;
    }

    @Override
    public void modifyOrder(int idOrder) {
         Connection con = getConnection();
        PreparedStatement ps;
        String sql = "UPDATE orders set state = ? WHERE id = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setBoolean(1, false);
            ps.setInt(2, idOrder);
            ps.execute();
        } catch (SQLException e) {
            System.out.println("Error al modificar pedido" + e.getMessage());
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println("Error al cerrar la conexion de mesa" + e.getMessage());
            }
        }
    }
    
    
}
