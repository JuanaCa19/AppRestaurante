/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.apprestaurante;

import com.mycompany.apprestaurante.Vista.viewAdmin.viewMainAdmin;
import com.formdev.flatlaf.FlatIntelliJLaf;
import com.mycompany.apprestaurante.Modelo.dao.UserDAO;
import com.mycompany.apprestaurante.Modelo.entities.User;
import com.mycompany.apprestaurante.Modelo.interfaces.IUserDAO;
import com.mycompany.apprestaurante.Vista.Login.inicio;
import javax.swing.UIManager;

/**
 *
 * @author carde
 */
public class AppRestaurante {

    public static void main(String[] args) {
        IUserDAO us = new UserDAO();
        us.saveUser(new User("Juan","Juanca19","admin"));
        try {
            UIManager.setLookAndFeel(new FlatIntelliJLaf());
        } catch (Exception e) {
            System.out.println("Error al aplicar FlatLaf");
        }
        inicio vista = new inicio();
        vista.setVisible(true);
    }
}
