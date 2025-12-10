package com.mycompany.apprestaurante.Modelo.connectionBD;

import java.sql.Connection;
import java.sql.DriverManager;

public class connection {
    public static Connection getConnection(){
        Connection connection = null;
        String user = "root";
        String password = "admin";
        String database = "restaurante";
        String url = "jdbc:mysql://localhost:3306/"+database;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url,user,password);
        }catch(Exception e){
            System.out.println("Error al conectar: " + e.getMessage());
        }
        return connection;
    }
}
