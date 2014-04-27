/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.netcracker.wind.commends.implementations.iedashboard;

import com.netcracker.wind.commands.ICommand;
import com.netcracker.wind.dao.factory.AbstractFactoryDAO;
import com.netcracker.wind.dao.factory.FactoryCreator;
import com.netcracker.wind.dao.interfaces.ICableDAO;
import com.netcracker.wind.dao.interfaces.IPortDAO;
import com.netcracker.wind.dao.interfaces.IServiceLocationDAO;
import com.netcracker.wind.entities.Cable;
import com.netcracker.wind.entities.Port;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Сашко
 */
public class CreateCable implements ICommand {
    
    public final String PORT_ID = "port_id";
    public final String SERVICE_LOCATION_ID = "service_location_id";

    public String execute(HttpServletRequest request, HttpServletResponse response) {
        int portID;
        int serviceLocationID;
        try {
            portID = Integer.parseInt(request.getParameter(PORT_ID));
            serviceLocationID = Integer.parseInt(request.getParameter(
                    SERVICE_LOCATION_ID));
        } catch (NumberFormatException exception) {
            return "";
        } 
        AbstractFactoryDAO factoryDAO = FactoryCreator.getInstance().getFactory();
        ICableDAO cableDAO = factoryDAO.createCableDAO();
        IServiceLocationDAO serviceLocationDAO = factoryDAO.createServiceLocationDAO();
        IPortDAO portDAO  = factoryDAO.createPortDAO();
        
        Cable cable = new Cable();
        Port port = portDAO.findByID(portID);
        port.setFree(false);
        portDAO.update(port);
        cable.setServiceLocation(serviceLocationDAO.findByID(serviceLocationID));
        cable.setPort(port);
        cableDAO.add(cable);
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
