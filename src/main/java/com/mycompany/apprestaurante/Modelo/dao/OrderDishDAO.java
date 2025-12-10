package com.mycompany.apprestaurante.Modelo.dao;

import com.mycompany.apprestaurante.Modelo.entities.OrderDish;
import com.mycompany.apprestaurante.Modelo.interfaces.IOrderDishDAO;

import java.util.List;

public class OrderDishDAO implements IOrderDishDAO {
    @Override
    public void saveOrderDish(OrderDish orderDish) {

    }

    @Override
    public List<OrderDish> listOrderDish() {
        return List.of();
    }
}
