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

public class OrderDAO implements IOrderDAO {
    @Override
    public void saveOrder(Order order) {

    }

    @Override
    public List<Order> listOrder() {
        return List.of();
    }

    @Override
    public List<orderTableDTO> findByIdWaiter(int idWaiter) {
        List<orderTableDTO> list = new ArrayList<>();
        Connection con = getConnection();
        PreparedStatement ps;
        ResultSet rs;
        String sql = "SELECT orders.id , orders.total,orders.idTable,tables.state FROM restaurante.orders\n" +
                "inner join tables on orders.idTable = tables.id where idWaiter = ?;";
        try{
            ps = con.prepareStatement(sql);
            ps.setInt(1,idWaiter);
            rs = ps.executeQuery();
            while(rs.next()){
                orderTableDTO orderTableDTO = new orderTableDTO();
                orderTableDTO.setTotal(rs.getInt("total"));
                orderTableDTO.setIdTable(rs.getInt("idTable"));
                orderTableDTO.setState(rs.getBoolean("state"));
                orderTableDTO.setIdOrder(rs.getInt("id"));
                list.add(orderTableDTO);
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
}
