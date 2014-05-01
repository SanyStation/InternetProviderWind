/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.wind.commands.implementations.order;

import com.netcracker.wind.commands.ICommand;
import com.netcracker.wind.dao.factory.AbstractFactoryDAO;
import com.netcracker.wind.dao.factory.FactoryCreator;
import com.netcracker.wind.dao.interfaces.IServiceInstanceDAO;
import com.netcracker.wind.dao.interfaces.IServiceOrderDAO;
import com.netcracker.wind.entities.ServiceInstance;
import com.netcracker.wind.entities.ServiceOrder;
import com.netcracker.wind.workflow.Workflow;
import java.sql.Timestamp;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Anatolii
 */
public class ConfirmOrder implements ICommand {

    private static final String ORDER = "order_id";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String sOrderId = request.getParameter(ORDER);
        int orderId;
        try {
            orderId = Integer.parseInt(sOrderId);
        } catch (NumberFormatException exception) {
            //TODO logging
            //TODO return error page
            return "";
        }
        AbstractFactoryDAO factoryDAO = FactoryCreator.getInstance().getFactory();
        IServiceOrderDAO serviceOrderDAO = factoryDAO.createServiceOrderDAO();
        IServiceInstanceDAO serviceInstanceDAO = factoryDAO.createServiceInstanceDAO();
        ServiceOrder order = serviceOrderDAO.findByID(orderId);
        if (order == null) {
            //TODO return error page
            return "";
        }

        order.setStatus(ServiceOrder.PROCESSING_STATUS);
        order.setProcesdate(new Timestamp(System.currentTimeMillis()));
        if (order.getScenario().equals(ServiceOrder.NEW_SCEARIO)) {
            ServiceInstance serviceInstance = new ServiceInstance();
            serviceInstance.setStatus(ServiceInstance.PLANNED);
            serviceInstance.setUser(order.getUser());
            serviceInstance.setServiceOrder(order);
            serviceInstance.setService(order.getService());
            serviceInstanceDAO.add(serviceInstance);
            order.setServiceInstance(serviceInstance);
            Workflow.createTaskForNewScnario(order);
        } else if (order.getScenario().equals(ServiceOrder.MODIFY_SCEARIO)) {
            Workflow.createTaskForModifyScenario(order);
        } else if (order.getScenario().equals(ServiceOrder.DISCONNECT_SCEARIO)) {
            Workflow.createTaskForDisconnectScenario(order);
        } else {
            //TODO return error page
            return "";
        }
        serviceOrderDAO.update(order);
        //TODO redirect to next page
        return "";
    }

}
