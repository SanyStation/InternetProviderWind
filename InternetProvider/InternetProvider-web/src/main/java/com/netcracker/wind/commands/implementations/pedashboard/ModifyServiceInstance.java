/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.wind.commands.implementations.pedashboard;

import com.netcracker.wind.annotations.RolesAllowed;
import com.netcracker.wind.commands.ICommand;
import com.netcracker.wind.dao.factory.AbstractFactoryDAO;
import com.netcracker.wind.dao.factory.FactoryCreator;
import com.netcracker.wind.dao.interfaces.IServiceInstanceDAO;
import com.netcracker.wind.dao.interfaces.ITaskDAO;
import com.netcracker.wind.entities.Role;
import com.netcracker.wind.entities.ServiceInstance;
import com.netcracker.wind.entities.Task;
import com.netcracker.wind.manager.ConfigurationManager;
import com.netcracker.wind.workflow.Workflow;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Anatolii
 */
@RolesAllowed(roles = Role.Roles.ProvisioningEngineer)
public class ModifyServiceInstance implements ICommand {

    private static final String TASK_ID = "task_id";
    private static final String TASK = "task";

    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        int taskId = Integer.parseInt(request.getParameter(TASK_ID));

        AbstractFactoryDAO factoryDAO = FactoryCreator.getInstance().getFactory();
        ITaskDAO taskDAO = factoryDAO.createTaskDAO();
        IServiceInstanceDAO serviceInstanceDAO = factoryDAO.createServiceInstanceDAO();

        Task task = taskDAO.findById(taskId);
        if (!task.getStatus().equals(Task.Status.ACTIVE)) {
            return manager.getProperty(ConfigurationManager.PAGE_PE_SELECTED_TASK);
        }

        ServiceInstance serviceInstance = task.getServiceOrder().getServiceInstance();
        serviceInstance.setService(task.getServiceOrder().getService());
        serviceInstance.setServiceOrder(task.getServiceOrder());
        serviceInstance.setStatus(ServiceInstance.Status.ACTIVE);
        serviceInstanceDAO.update(serviceInstance);
        task.setStatus(Task.Status.COMPLETED);
        taskDAO.update(task);
        request.setAttribute(TASK, task);
        Workflow.createTaskForCSE(task.getServiceOrder(), taskDAO);

        return manager.getProperty(ConfigurationManager.PAGE_PE_SELECTED_TASK);
    }

}
