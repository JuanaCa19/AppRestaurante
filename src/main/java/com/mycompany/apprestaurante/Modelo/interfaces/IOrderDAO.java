package com.mycompany.apprestaurante.Modelo.interfaces;

import com.mycompany.apprestaurante.Modelo.dto.orderTableDTO;
import com.mycompany.apprestaurante.Modelo.entities.Dish;
import com.mycompany.apprestaurante.Modelo.entities.Order;

import java.util.List;

public interface IOrderDAO {

    public void saveOrder(Order order);

    public List<Order> listOrder();

    public List<orderTableDTO> findByIdWaiter(int idWaiter);
}
