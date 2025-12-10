package com.mycompany.apprestaurante.Modelo.dao;

import com.mycompany.apprestaurante.Modelo.entities.Order;
import com.mycompany.apprestaurante.Modelo.interfaces.IOrderDAO;

import java.util.List;

public class OrderDAO implements IOrderDAO {
    @Override
    public void saveOrder(Order order) {

    }

    @Override
    public List<Order> listOrder() {
        return List.of();
    }
}
