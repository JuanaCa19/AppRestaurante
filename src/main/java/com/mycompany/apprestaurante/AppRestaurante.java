/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.apprestaurante;

import com.mycompany.apprestaurante.Vista.viewAdmin.viewMainAdmin;
import com.formdev.flatlaf.FlatIntelliJLaf;
import com.mycompany.apprestaurante.Vista.Login.inicio;
import javax.swing.UIManager;

/**
 *
 * @author carde
 */
public class AppRestaurante {

    public static void main(String[] args) {
                try {
            UIManager.setLookAndFeel(new FlatIntelliJLaf());
        } catch (Exception e) {
            System.out.println("Error al aplicar FlatLaf");
        }
        inicio vista = new inicio(); 
        vista.setVisible(true);
    }
}
