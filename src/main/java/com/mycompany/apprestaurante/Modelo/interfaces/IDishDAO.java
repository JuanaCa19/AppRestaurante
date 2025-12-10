package com.mycompany.apprestaurante.Modelo.interfaces;

import com.mycompany.apprestaurante.Modelo.entities.Dish;

import java.util.List;

public interface IDishDAO {

    public void saveDish(Dish dish);

    public List<Dish> listDish();
}
