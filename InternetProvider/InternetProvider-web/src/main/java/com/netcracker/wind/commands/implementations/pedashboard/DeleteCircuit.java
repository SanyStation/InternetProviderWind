/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.wind.commands.implementations.pedashboard;

import com.netcracker.wind.commands.ICommand;
import com.netcracker.wind.dao.factory.AbstractFactoryDAO;
import com.netcracker.wind.dao.factory.FactoryCreator;
import com.netcracker.wind.dao.interfaces.ICircuitDAO;
import com.netcracker.wind.dao.interfaces.IPortDAO;
import com.netcracker.wind.entities.Circuit;
import com.netcracker.wind.entities.Port;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Anatolii
 */
public class DeleteCircuit implements ICommand {

    public static final String CIRCUIT_ID = "circuit_id";

    public String execute(HttpServletRequest request, HttpServletResponse response) {
        int circuitId = -1;
        try {
            circuitId = Integer.parseInt(request.getParameter(CIRCUIT_ID));
        } catch (NumberFormatException exception) {
            //TODO return error page
            return "";
        }
        if (circuitId == -1) {
            //TODO return error page
            return "";
        }
        AbstractFactoryDAO factoryDAO = FactoryCreator.getInstance().getFactory();
        ICircuitDAO circuitDAO = factoryDAO.createCircuitDAO();
        IPortDAO portDAO = factoryDAO.createPortDAO();
        Circuit circuit = circuitDAO.findByID(circuitId);
        Port port = circuit.getPort();
        port.setFree(true);
        portDAO.update(port);
        circuitDAO.delete(circuit.getId());
        //TODO return to next page
        return "";
    }

}
