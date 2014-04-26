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
import com.netcracker.wind.entities.Task;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Anatolii
 */
public class ProvisioningEngineerTasks implements ICommand {

    private static final int PROVISIONING_ENGINEER_TASKS = 2;
    private static final String FROM = "from";
    private static final String NUMBER = "number";
    private static final String TASKS = "tasks";
    private static final String SIZE = "size";
    private static final String LAST_NUMBER = "last_number";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String sFrom = request.getParameter(FROM);
        String sNumber = request.getParameter(NUMBER);
        int from;
        int number;
        try {
            from = Integer.parseInt(sFrom);
            number = Integer.parseInt(sNumber);
        } catch (NumberFormatException exception) {
            //TODO to error page
            return "";
        }

        AbstractFactoryDAO factoryDAO = FactoryCreator.getInstance().getFactory();
        ITaskDAO taskDAO = factoryDAO.createTaskDAO();
        List<Task> tasks = taskDAO.findByGroup(PROVISIONING_ENGINEER_TASKS, from, number);
        request.setAttribute(TASKS, tasks);
        request.setAttribute(SIZE, tasks.size());
        request.setAttribute(LAST_NUMBER, from + number);
        return "/listOfTasksForPE.jsp";
//        return DashboardsUtilities.getTaskJSON(PROVISIONING_ENGINEER_TASKS);
    }

}
