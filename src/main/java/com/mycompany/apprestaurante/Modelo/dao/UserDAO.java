package com.mycompany.apprestaurante.Modelo.dao;

import com.mycompany.apprestaurante.Modelo.Seguridad.Encriptador;
import com.mycompany.apprestaurante.Modelo.entities.User;
import com.mycompany.apprestaurante.Modelo.interfaces.IUserDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import static com.mycompany.apprestaurante.Modelo.connectionBD.connection.getConnection;

public class UserDAO implements IUserDAO {
    
    @Override
    public void saveUser(User user) {
        Connection con = getConnection();
        PreparedStatement ps;
        String sql = "INSERT INTO users (name,nameUser,password) VALUES (?,?,?)";
        String password = Encriptador.encriptarPassword(user.getPassword());
        try{
            ps = con.prepareStatement(sql);
            ps.setString(1, user.getName());
            ps.setString(2, user.getNameUser());
            ps.setString(3, password);
            ps.execute();

        }catch(SQLException e){
            System.out.println("Error al insertar usuario: " + e.getMessage());
        }finally{
            try{
                con.close();
            }catch(SQLException e){
                System.out.println("Error al cerrar la conexion: " + e.getMessage());
            }
        }
    }

    @Override
    public List<User> listUser() {
        List<User> list = new ArrayList<>();
        Connection con = getConnection();
        PreparedStatement ps;
        ResultSet rs;
        String sql = "SELECT * FROM users";
        try{
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                User user = new User();
                user.setName(rs.getString("name"));
                user.setId(rs.getInt("id"));
                user.setNameUser(rs.getString("nameUser"));
                list.add(user);
            }
            return list;
        }catch(SQLException e){
            System.out.println("Error al listar usuarios: " + e.getMessage());
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
    public boolean checkCredentials(String nameUser,String password) {
        Connection con = getConnection();
        PreparedStatement ps;
        ResultSet rs;
        String sql = "SELECT password FROM users WHERE nameUser = ?";
        try{
            ps = con.prepareStatement(sql);
            ps.setString(1, nameUser);
            rs = ps.executeQuery();
            if(rs.next()){
                boolean resp = Encriptador.validatePassword(password,rs.getString("password"));
                if(resp){
                    return true;
                }
            }
        }catch(SQLException e){
            System.out.println("Error al validar: " + e.getMessage());
        }finally{
            try{
                con.close();
            }catch(SQLException e){
                System.out.println("Error al cerrar la conexion: " + e.getMessage());
            } 
        }
        return false;
    }
}
