package com.mycompany.apprestaurante.Controlador.orderController;

import com.mycompany.apprestaurante.Modelo.dao.OrderDAO;
import com.mycompany.apprestaurante.Modelo.dto.orderTableDTO;
import com.mycompany.apprestaurante.Modelo.interfaces.IOrderDAO;

import java.util.List;

public class OrderController {

    private IOrderDAO iOrderDAO = new OrderDAO();

    public List<orderTableDTO> findByIdWaiter(int idWaiter){
        return iOrderDAO.findByIdWaiter(idWaiter);
    }
}
