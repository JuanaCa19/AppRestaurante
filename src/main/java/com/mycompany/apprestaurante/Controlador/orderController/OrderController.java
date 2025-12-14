package com.mycompany.apprestaurante.Controlador.orderController;

import com.mycompany.apprestaurante.Controlador.orderDishController.OrderDishController;
import com.mycompany.apprestaurante.Modelo.dao.OrderDAO;
import com.mycompany.apprestaurante.Modelo.entities.Dish;
import com.mycompany.apprestaurante.Modelo.entities.Order;
import com.mycompany.apprestaurante.Modelo.interfaces.IOrderDAO;

import java.util.List;

public class OrderController {

    private IOrderDAO iOrderDAO = new OrderDAO();
    private OrderDishController orderDishController = new OrderDishController();

    public List<Order> findByIdWaiter(int idWaiter){
        return iOrderDAO.findByIdWaiter(idWaiter);
    }

    public void saveOrder(int idTable,int idWaiter,double price,List<Dish> dishSelected){
        Order order = new Order();
        order.setIdTable(idTable);
        order.setIdWaiter(idWaiter);
        order.setTotal(price);
        int idOrder = iOrderDAO.saveOrder(order);
        orderDishController.saveOrderDish(idOrder,dishSelected);
    }
    public void modifyOrder(int idOrder){
        iOrderDAO.modifyOrder(idOrder);
    }
}
