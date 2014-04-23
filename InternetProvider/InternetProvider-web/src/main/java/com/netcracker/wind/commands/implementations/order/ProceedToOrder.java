/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.wind.commands.implementations.order;

import com.netcracker.wind.commands.ICommand;
import com.netcracker.wind.dao.factory.AbstractFactoryDAO;
import com.netcracker.wind.dao.factory.FactoryCreator;
import com.netcracker.wind.dao.interfaces.IProviderLocationDAO;
import com.netcracker.wind.dao.interfaces.IServiceLocationDAO;
import com.netcracker.wind.dao.interfaces.IServiceOrderDAO;
import com.netcracker.wind.dao.interfaces.IUserDAO;
import com.netcracker.wind.entities.ProviderLocation;
import com.netcracker.wind.entities.Service;
import com.netcracker.wind.entities.ServiceLocation;
import com.netcracker.wind.entities.ServiceOrder;
import com.netcracker.wind.entities.User;
import java.sql.Date;
import java.util.List;
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
            return "no session";
            //TODO redirect to login or register page
        }
        Object paramNameUser = session.getAttribute(NAME);
        if (paramNameUser == null) {
            //TODO redirect to login or register page
            return "no user";
        }
        String email = (String) paramNameUser;

        String sX = request.getParameter("x");
        String sY = request.getParameter("y");
        String sID = request.getParameter("serviceId");
//        String plID = request.getParameter("providerLovationID");
        //TODO check valid parameter
        double actualX = Double.parseDouble(sX);
        double actualY = Double.parseDouble(sY);
        int serviceID = Integer.parseInt(sID);
//        int providerLocationID = Integer.parseInt(plID);

        if (!isCoordinatesValid(actualX, actualY)) {
            //TODO redirect to error page
            return "";
        }

        AbstractFactoryDAO factoryDAO = FactoryCreator.getInstance().getFactory();
        IUserDAO userDAO = factoryDAO.createUserDAO();
        IServiceOrderDAO orderDAO = factoryDAO.createServiceOrderDAO();
        IServiceLocationDAO serviceLocationDAO = factoryDAO.createServiceLocationDAO();
        IProviderLocationDAO providerLocationDAO = factoryDAO.createProviderLocationDAO();

        User user = userDAO.findByEmail(email);
        //Find nearest ProviderLocation
        List<ProviderLocation> providerLocations = providerLocationDAO.findAll();
        ProviderLocation nearestProviderLocation = OrderUtilities.findNearestProviderLocation(
                providerLocations, actualX, actualY);

//        //TODO validation
//        if(providerLocationID != nearestProviderLocation.getId()){
//            return "";
//        }
        ServiceOrder order = new ServiceOrder();
        order.setEnterdate(new Date(System.currentTimeMillis()));
        order.setUsers(user);
        order.setServices(new Service(serviceID));
        order.setProviderLocations(nearestProviderLocation);
        ServiceLocation serviceLocation = new ServiceLocation();
        serviceLocation.setPosX(actualX);
        serviceLocation.setPosY(actualY);
        serviceLocationDAO.add(serviceLocation);
        //TODO configure servise location
        order.setServiceLocations(serviceLocation);
        order.setStatus(ServiceOrder.ENTERING);
        order.setScenario("new");
        orderDAO.add(order);
        //TODO redirect to next page
        return "";
    }

    private boolean isCoordinatesValid(double x, double y) {
        return true;
    }

}
