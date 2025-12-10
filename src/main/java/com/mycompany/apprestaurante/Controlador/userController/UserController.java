/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.apprestaurante.Controlador.userController;

import com.mycompany.apprestaurante.Modelo.dao.UserDAO;
import com.mycompany.apprestaurante.Modelo.interfaces.IUserDAO;
import com.mycompany.apprestaurante.Vista.viewAdmin.viewMainAdmin;
import javax.swing.JOptionPane;

/**
 *
 * @author carde
 */
public class UserController {
    private IUserDAO dao = new UserDAO();
    
    public boolean validateLogin(String nameUser,String password){
        boolean vali = this.dao.checkCredentials(nameUser,password);
        if(vali){
            JOptionPane.showMessageDialog(null, "Bienvenido " + nameUser);
            new viewMainAdmin().setVisible(true);
            return true;
        }
        JOptionPane.showMessageDialog(null, "Contraseña o Usuario Incorrecto!!!");
        return false;
    }
}
