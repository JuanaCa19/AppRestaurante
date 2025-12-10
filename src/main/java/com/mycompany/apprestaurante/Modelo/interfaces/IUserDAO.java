package com.mycompany.apprestaurante.Modelo.interfaces;

import com.mycompany.apprestaurante.Modelo.entities.Dish;
import com.mycompany.apprestaurante.Modelo.entities.User;

import java.util.List;

public interface IUserDAO {

    public void saveUser(User user);

    public List<User> listUser();
    
    public boolean checkCredentials(String nameUser,String password);
}
