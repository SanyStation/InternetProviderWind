/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.netcracker.wind.commands.implementations.iedashboard;

import com.netcracker.wind.commands.ICommand;
import com.netcracker.wind.dao.factory.AbstractFactoryDAO;
import com.netcracker.wind.dao.factory.FactoryCreator;
import com.netcracker.wind.dao.interfaces.ICableDAO;
import com.netcracker.wind.dao.interfaces.IPortDAO;
import com.netcracker.wind.entities.Cable;
import com.netcracker.wind.entities.Port;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Сашко
 */
public class DeleteCable implements ICommand{
    
    public final String CABLE_ID = "cable_id";

    public String execute(HttpServletRequest request, HttpServletResponse response) {
        
        int cableID;
        try {
            cableID = Integer.parseInt(request.getParameter(CABLE_ID));
        } catch (NumberFormatException exception) {
            return "";
        } 
        
        AbstractFactoryDAO factoryDAO = FactoryCreator.getInstance().getFactory();
        ICableDAO cableDAO = factoryDAO.createCableDAO();
        IPortDAO portDAO = factoryDAO.createPortDAO();
        
        Cable cable = cableDAO.findByID(cableID);
        Port port = cable.getPort();
        port.setFree(true);
        port.setCable(null); 
        portDAO.update(port);
        cableDAO.delete(cableID);
        
        throw new UnsupportedOperationException("Not supported yet."); 
    }
    
}
