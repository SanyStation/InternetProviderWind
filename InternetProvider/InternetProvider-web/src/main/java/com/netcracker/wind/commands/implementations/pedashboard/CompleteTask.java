/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.wind.commands.implementations.pedashboard;

import com.netcracker.wind.commands.ICommand;
import com.netcracker.wind.dao.factory.AbstractFactoryDAO;
import com.netcracker.wind.dao.factory.FactoryCreator;
import com.netcracker.wind.dao.interfaces.ITaskDAO;
import com.netcracker.wind.entities.ServiceOrder;
import com.netcracker.wind.entities.Task;
import com.netcracker.wind.workflow.NewScenarioWorkflow;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Anatolii
 */
public class CompleteTask implements ICommand {

    private static final String TASK_ID = "task_id";

    public String execute(HttpServletRequest request, HttpServletResponse response) {
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
        Task task = taskDAO.findByID(taskId);
        //TODO validation is it the same user who login?
        if (!task.getStatus().equals(Task.TaskStatus.ACTIVE.toString())) {
            //TODO return error page
        }

        task.setStatus(Task.TaskStatus.ACTIVE.toString());
        ServiceOrder order = task.getServiceOrder();
        taskDAO.update(task);
        NewScenarioWorkflow.createTaskForCSE(order, taskDAO);
        //TODO return next page
        return "";
    }

}
