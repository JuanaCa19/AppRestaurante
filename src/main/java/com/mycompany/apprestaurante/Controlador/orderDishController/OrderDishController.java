package com.mycompany.apprestaurante.Controlador.orderDishController;

import com.mycompany.apprestaurante.Modelo.dao.OrderDishDAO;
import com.mycompany.apprestaurante.Modelo.entities.Dish;
import com.mycompany.apprestaurante.Modelo.entities.OrderDish;
import com.mycompany.apprestaurante.Modelo.interfaces.IOrderDishDAO;

import java.util.List;

public class OrderDishController {

    private IOrderDishDAO orderDishDAO = new OrderDishDAO();

    public List<Dish> findByIdOrder(int idOrder){
        return  orderDishDAO.findByIdOrder(idOrder);
    }

    public void saveOrderDish(int idOrder,List<Dish> dishSelected){
        for(Dish dish:dishSelected){
            orderDishDAO.saveOrderDish(new OrderDish(idOrder,dish.getId()));
        }
    }
}
