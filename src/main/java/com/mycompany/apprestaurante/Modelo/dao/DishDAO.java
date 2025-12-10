package com.mycompany.apprestaurante.Modelo.dao;

import com.mycompany.apprestaurante.Modelo.entities.Dish;
import com.mycompany.apprestaurante.Modelo.interfaces.IDishDAO;

import java.util.List;

public class DishDAO implements IDishDAO {
    @Override
    public void saveDish(Dish dish) {

    }

    @Override
    public List<Dish> listDish() {
        return List.of();
    }
}
