package com.mycompany.apprestaurante.Modelo.dao;


import com.mycompany.apprestaurante.Modelo.entities.Waiter;
import com.mycompany.apprestaurante.Modelo.interfaces.IWaiterDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.mycompany.apprestaurante.Modelo.connectionBD.connection.getConnection;

public class WaiterDAO implements IWaiterDAO {
    @Override
    public void saveWaiter(Waiter waiter) {
        Connection con = getConnection();
        PreparedStatement ps;
        String sql = "INSERT INTO waiters (name,phone) VALUES (?,?)";
        try{
            ps = con.prepareStatement(sql);
            ps.setString(1, waiter.getName());
            ps.setString(2, waiter.getPhone());
            ps.execute();

        }catch(SQLException e){
            System.out.println("Error al insertar mesero: " + e.getMessage());
        }finally{
            try{
                con.close();
            }catch(SQLException e){
                System.out.println("Error al cerrar la conexion: " + e.getMessage());
            }
        }
    }

    @Override
    public List<Waiter> listWaiter() {
        List<Waiter> list = new ArrayList<>();
        Connection con = getConnection();
        PreparedStatement ps;
        ResultSet rs;
        String sql = "SELECT * FROM waiters";
        try{
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                Waiter waiter = new Waiter();
                waiter.setName(rs.getString("name"));
                waiter.setId(rs.getInt("id"));
                waiter.setPhone(rs.getString("phone"));
                list.add(waiter);
            }
            return list;
        }catch(SQLException e){
            System.out.println("Error al listar meseros: " + e.getMessage());
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
