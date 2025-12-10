package com.mycompany.apprestaurante.Controlador.waiterController;

import com.mycompany.apprestaurante.Modelo.dao.WaiterDAO;
import com.mycompany.apprestaurante.Modelo.entities.Waiter;
import com.mycompany.apprestaurante.Modelo.interfaces.IWaiterDAO;

import java.util.List;

public class WaiterController {
    private IWaiterDAO dao = new WaiterDAO();

    public List<Waiter> listWaiter(){
        return dao.listWaiter();
    }
}
