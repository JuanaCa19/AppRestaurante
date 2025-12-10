/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.apprestaurante.Modelo.Seguridad;

import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author carde
 */
public class Encriptador {
    public static String encriptarPassword(String password){
        return BCrypt.hashpw(password,BCrypt.gensalt(12));
    }
    
    public static boolean validatePassword(String password,String hash){
        return BCrypt.checkpw(password, hash);
    }
}
