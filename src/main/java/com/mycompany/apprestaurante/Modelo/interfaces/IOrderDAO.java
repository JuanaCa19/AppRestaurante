package com.mycompany.apprestaurante.Modelo.interfaces;

import com.mycompany.apprestaurante.Modelo.dto.orderTableDTO;
import com.mycompany.apprestaurante.Modelo.entities.Dish;
import com.mycompany.apprestaurante.Modelo.entities.Order;

import java.util.List;

public interface IOrderDAO {

    public int saveOrder(Order order);

    public List<Order> listOrder();

    public List<Order> findByIdWaiter(int idWaiter);
    
    public void modifyOrder(int idOrder);
    
     public int getActiveOrders();  
     
     public double obtenerVentasDelDia(); 
}
