/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.wind.commands.implementations.csedashboard;

import com.netcracker.wind.annotations.RolesAllowed;
import com.netcracker.wind.commands.ICommand;
import com.netcracker.wind.dao.factory.AbstractFactoryDAO;
import com.netcracker.wind.dao.factory.FactoryCreator;
import com.netcracker.wind.dao.interfaces.IServiceInstanceDAO;
import com.netcracker.wind.dao.interfaces.IServiceOrderDAO;
import com.netcracker.wind.dao.interfaces.ITaskDAO;
import com.netcracker.wind.entities.Role;
import com.netcracker.wind.entities.ServiceInstance;
import com.netcracker.wind.entities.ServiceOrder;
import com.netcracker.wind.entities.Task;
import com.netcracker.wind.entities.User;
import com.netcracker.wind.mail.FormatedMail;
import com.netcracker.wind.mail.MailSendler;
import com.netcracker.wind.manager.ConfigurationManager;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Oksana
 */
@RolesAllowed(roles = Role.Roles.CustomerSupportEngineer)
public class CSEsentBill implements ICommand {

    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("user");
        AbstractFactoryDAO factoryDAO = FactoryCreator.getInstance().getFactory();
        ITaskDAO taskDAO = factoryDAO.createTaskDAO();
        IServiceOrderDAO orderDAO = factoryDAO.createServiceOrderDAO();
        IServiceInstanceDAO serviceInstanceDAO = factoryDAO.createServiceInstanceDAO();

        int taskId = Integer.parseInt(request.getParameter("task_id"));

        Task task = taskDAO.findById(taskId);
        if (!task.getStatus().equals(Task.Status.ACTIVE)) {
            request.setAttribute("task", task);
            return manager.getProperty(ConfigurationManager.PAGE_CSE_SELECTED_TASK);
        }

        task.setStatus(Task.Status.COMPLETED);

        ServiceOrder order = task.getServiceOrder();
        ServiceInstance serviceInstance = order.getServiceInstance();
        order.setCompletedate(new Timestamp(System.currentTimeMillis()));
        order.setStatus(ServiceOrder.Status.COMPLETED);
        orderDAO.update(order);
        if (order.getScenario().equals(ServiceOrder.Scenario.NEW)) {
            serviceInstance.setStatus(ServiceInstance.Status.ACTIVE);
            serviceInstanceDAO.update(serviceInstance);
        }

        taskDAO.update(task);
        request.setAttribute("task", task);
        
        List<User> users = new ArrayList<User>();
        users.add(order.getUser());
        
        new MailSendler().sendEmail(users, "Boreas Bill", new FormatedMail().getSentBillMassage(order.getServiceInstance(), order.getUser()));
        return manager.getProperty(ConfigurationManager.PAGE_CSE_SELECTED_TASK);
    }

}
