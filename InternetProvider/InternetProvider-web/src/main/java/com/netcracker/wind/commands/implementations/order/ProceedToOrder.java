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
import com.netcracker.wind.manager.ConfigurationManager;
import java.sql.Timestamp;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Anatolii
 */
public class ProceedToOrder implements ICommand {

    private static final String NAME = "name";

    public String execute(HttpServletRequest request, HttpServletResponse response) {
        JSONObject jsono = new JSONObject();
        HttpSession session = request.getSession(false);
        if (session == null) {
            return processError(jsono);
        }
        Object paramNameUser = session.getAttribute(NAME);
        if (paramNameUser == null) {
            return processError(jsono);
        }
        String email = (String) paramNameUser;

        String sX = request.getParameter("x");
        String sY = request.getParameter("y");
        String sID = request.getParameter("serviceId");
        String address = request.getParameter("address");
        double actualX = Double.parseDouble(sX);
        double actualY = Double.parseDouble(sY);
        int serviceId = Integer.parseInt(sID);
////        int providerLocationID = Integer.parseInt(plID);
//
//        if (!isCoordinatesValid(actualX, actualY)) {
//            //TODO redirect to error page
//            return "";
//        }

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

        ServiceOrder order = new ServiceOrder();
        order.setEnterdate(new Timestamp(System.currentTimeMillis()));
        order.setUser(user);
        //Hier new Service(serviceID)
        order.setService(new Service(serviceId));
        order.setProviderLocation(nearestProviderLocation);
        ServiceLocation serviceLocation = new ServiceLocation();
        serviceLocation.setPosX(actualX);
        serviceLocation.setPosY(actualY);
        serviceLocation.setAddress(address);
        serviceLocationDAO.add(serviceLocation);
        //TODO configure servise location
        order.setServiceLocation(serviceLocation);
        order.setStatus(ServiceOrder.ENTERING_STATUS);
        order.setScenario("new");
        orderDAO.add(order);
        try {
            jsono.put("auth", true);
            jsono.put("nextPage", ConfigurationManager.getInstance().
                    getProperty(ConfigurationManager.PAGE_CONFIRM_ORDER));
        } catch (JSONException ex) {
            Logger.getLogger(ProceedToOrder.class.getName()).log(Level.SEVERE, null, ex);
        }
        //TODO redirect to next page
        return jsono.toString();
    }

    private boolean isCoordinatesValid(double x, double y) {
        return true;
    }

    private String processError(JSONObject jsono) {
        try {
            jsono.put("auth", false);
            jsono.put("nextPage", ConfigurationManager.getInstance().
                    getProperty(ConfigurationManager.PAGE_LOGIN));
        } catch (JSONException ex) {
            Logger.getLogger(ProceedToOrder.class.getName()).log(Level.SEVERE, null, ex);
        }
        return jsono.toString();
    }

}
