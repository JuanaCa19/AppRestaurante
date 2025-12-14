package com.mycompany.apprestaurante.Modelo.interfaces;

import com.mycompany.apprestaurante.Modelo.entities.Waiter;

import java.util.List;

public interface IWaiterDAO {

    public boolean saveWaiter(Waiter waiter);

    public List<Waiter> listWaiter();

    public int findByName(String name);
    
    public int getActiveWaiters(); 
}
