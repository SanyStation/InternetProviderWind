/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.wind.commands.implementations.order;

import com.netcracker.wind.commands.ICommand;
import com.netcracker.wind.dao.factory.AbstractFactoryDAO;
import com.netcracker.wind.dao.factory.FactoryCreator;
import com.netcracker.wind.dao.interfaces.IServiceOrderDAO;
import com.netcracker.wind.dao.interfaces.IUserDAO;
import com.netcracker.wind.entities.ProviderLocation;
import com.netcracker.wind.entities.Service;
import com.netcracker.wind.entities.ServiceLocation;
import com.netcracker.wind.entities.ServiceOrder;
import com.netcracker.wind.entities.User;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Anatolii
 */
public class ProceedToOrder implements ICommand {

    private static final String NAME = "name";

    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            //TODO redirect to login or register page
        }
        Object paramNameUser = session.getAttribute(NAME);
        if (paramNameUser == null) {
            //TODO redirect to login or register page
        }
        AbstractFactoryDAO factoryDAO = FactoryCreator.getInstance().getFactory();
        IUserDAO userDAO = factoryDAO.createUserDAO();
        IServiceOrderDAO orderDAO = factoryDAO.createServiceOrderDAO();
        User user = userDAO.findByEmail(NAME);

        String sID = request.getParameter("serviceId");
        String plID = request.getParameter("providerLovationID");
        if (sID == null || plID == null) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
        //TODO do I need to check correct input data?
        int serviceID = Integer.parseInt(sID);
        int providerLocationID = Integer.parseInt(plID);

        ServiceOrder order = new ServiceOrder();
        order.setEnterdate(new Date());
        order.setUsers(user);
        order.setServices(new Service(serviceID));
        order.setProviderLocations(new ProviderLocation(providerLocationID));
        ServiceLocation serviceLocation = new ServiceLocation();
        //TODO configure servise location
        order.setServiceLocations(serviceLocation);
        order.setStatus(ServiceOrder.ENTERING);

        orderDAO.add(order);
        //TODO redirect to next page
        return "";
    }

}
