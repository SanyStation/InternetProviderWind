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
import com.netcracker.wind.dao.interfaces.IServiceInstanceDAO;
import com.netcracker.wind.entities.Circuit;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Anatolii
 */
public class CreateCircuit implements ICommand {

    private static final String PORT_ID = "port_id";
    private static final String SERVICE_INSTANCE_ID = "service_instance_id";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        int portID;
        int serviceInstanceID;
        try {
            portID = Integer.parseInt(request.getParameter(PORT_ID));
            serviceInstanceID = Integer.parseInt(request.getParameter(SERVICE_INSTANCE_ID));
        } catch (NumberFormatException exception) {
            //TODO log
            //TODO redirecct to error page
            return "";
        }
        AbstractFactoryDAO factoryDAO = FactoryCreator.getInstance().getFactory();
        IPortDAO portDAO = factoryDAO.createPortDAO();
        ICircuitDAO circuitDAO = factoryDAO.createCircuitDAO();
        IServiceInstanceDAO serviceInstanceDAO = factoryDAO.createServiceInstanceDAO();

        Circuit circuit = new Circuit();
        circuit.setPorts(portDAO.findByID(portID));
        circuit.setServiceInstance(serviceInstanceDAO.findByID(serviceInstanceID));
        circuitDAO.add(circuit);
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
