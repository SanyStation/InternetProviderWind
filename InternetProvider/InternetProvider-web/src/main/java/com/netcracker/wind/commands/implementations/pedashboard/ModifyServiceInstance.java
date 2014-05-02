/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.wind.commands.implementations.pedashboard;

import com.netcracker.wind.commands.ICommand;
import com.netcracker.wind.dao.factory.AbstractFactoryDAO;
import com.netcracker.wind.dao.factory.FactoryCreator;
import com.netcracker.wind.dao.interfaces.IServiceInstanceDAO;
import com.netcracker.wind.dao.interfaces.ITaskDAO;
import com.netcracker.wind.entities.ServiceInstance;
import com.netcracker.wind.entities.Task;
import com.netcracker.wind.entities.User;
import com.netcracker.wind.workflow.Workflow;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Anatolii
 */
public class ModifyServiceInstance implements ICommand {

    private static final String TASK_ID = "task_id";

    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return "";
        }

        int taskId = -1;
        try {
            taskId = Integer.parseInt(request.getParameter(TASK_ID));
        } catch (NumberFormatException exception) {
            //TODO logging
            //TODO return error page
        }
        if (taskId == -1) {
            //TODO return error page
            return "";
        }

        AbstractFactoryDAO factoryDAO = FactoryCreator.getInstance().getFactory();
        ITaskDAO taskDAO = factoryDAO.createTaskDAO();
        IServiceInstanceDAO serviceInstanceDAO = factoryDAO.createServiceInstanceDAO();

        Task task = taskDAO.findByID(taskId);
        if (!task.getType().equals(Task.Type.MODIFY_CIRCUIT)) {
            //TODO return error page
            return "";
        }

        ServiceInstance serviceInstance = task.getServiceOrder().getServiceInstance();
        serviceInstance.setService(task.getServiceOrder().getService());
        serviceInstanceDAO.update(serviceInstance);
        task.setStatus(Task.Status.COMPLETED);
        taskDAO.update(task);
        Workflow.createTaskForCSE(task.getServiceOrder(), taskDAO);
        
        //TODO return next page
        return "";
    }

}
