/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.netcracker.wind.commends.implementations.iedashboard;

import com.netcracker.wind.commands.ICommand;
import com.netcracker.wind.dao.factory.AbstractFactoryDAO;
import com.netcracker.wind.dao.factory.FactoryCreator;
import com.netcracker.wind.dao.interfaces.IDeviceDAO;
import com.netcracker.wind.dao.interfaces.IPortDAO;
import com.netcracker.wind.entities.Device;
import com.netcracker.wind.entities.Port;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Сашко
 */
public class CreateDevice implements ICommand {
    
    public static final String D_NAME = "d_name"; 
    public static final int PORT_N = 60;

    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String dName;
        dName = request.getParameter(D_NAME);
        AbstractFactoryDAO factoryDAO = FactoryCreator.getInstance().getFactory();
        IDeviceDAO deviceDAO = factoryDAO.createDeviceDAO();
        IPortDAO portDAO = factoryDAO.createPortDAO();
        
        Device device = new Device();
        device.setName(dName);
        deviceDAO.add(device);
     
        Port port = new Port();
        
        for (int i = 0; i != PORT_N; i++){
            portDAO.add(port);
        }
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
