/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.apprestaurante.Controlador.dishController;

import com.mycompany.apprestaurante.Modelo.dao.DishDAO;
import com.mycompany.apprestaurante.Modelo.entities.Dish;
import com.mycompany.apprestaurante.Modelo.interfaces.IDishDAO;
import java.util.List;

/**
 *
 * @author carlo
 */
public class DishController {

    private IDishDAO dishDAO = new DishDAO();

    public boolean insertDish(Dish dish) {
        return dishDAO.saveDish(dish);
    }

    public List<Dish> listdish() {
        return dishDAO.listDish();
    }
}
