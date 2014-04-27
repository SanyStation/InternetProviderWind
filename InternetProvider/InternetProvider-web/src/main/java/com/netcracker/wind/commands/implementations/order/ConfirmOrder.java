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
import com.netcracker.wind.dao.interfaces.ITaskDAO;
import com.netcracker.wind.entities.ServiceInstance;
import com.netcracker.wind.entities.ServiceOrder;
import com.netcracker.wind.entities.Task;
import com.netcracker.wind.workflow.NewScenarioWorkflow;
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
        ServiceInstance serviceInstance = new ServiceInstance();
        serviceInstance.setStatus(ServiceInstance.PLANNED);
        serviceInstance.setUser(order.getUser());
        serviceInstance.setServiceOrder(order);
        serviceInstance.setService(order.getService());
        serviceInstanceDAO.add(serviceInstance);

        order.setStatus(ServiceOrder.PROCESSING_STATUS);
        order.setProcesdate(new Timestamp(System.currentTimeMillis()));
        order.setServiceInstance(serviceInstance);
        serviceOrderDAO.update(order);
        Task task = NewScenarioWorkflow.createTaskForNewScnario(order);
        ITaskDAO taskDAO = factoryDAO.createTaskDAO();
        taskDAO.add(task);
        //TODO redirect to next page
        return "";
    }

}
