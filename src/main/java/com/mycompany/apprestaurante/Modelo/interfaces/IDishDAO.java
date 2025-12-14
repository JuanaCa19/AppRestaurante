package com.mycompany.apprestaurante.Modelo.interfaces;

import com.mycompany.apprestaurante.Modelo.entities.Dish;

import java.util.List;

public interface IDishDAO {

    public boolean saveDish(Dish dish);

    public List<Dish> listDish();
}
