package com.mycompany.apprestaurante.Modelo.interfaces;

import com.mycompany.apprestaurante.Modelo.entities.Dish;
import com.mycompany.apprestaurante.Modelo.entities.OrderDish;

import java.util.List;

public interface IOrderDishDAO {

    public void saveOrderDish(OrderDish orderDish);

    public List<OrderDish> listOrderDish();

    public List<Dish> findByIdOrder(int idOrder);
}
