/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.netcracker.wind.commands.implementations.iedashboard;

import com.netcracker.wind.commands.DashboardsUtilities;
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
 * @author Сашко
 */
public class GetActiveIETasks implements ICommand {

    private static final String TASKS_DEV = "tasks_dev";
    private static final String TASKS_CABL = "tasks_cabl";
    private static final String TASKS_DEL_CABL = "tasks_del_cabl";
    private static final String SIZE_DEV = "size_dev";
    private static final String SIZE_CABL = "size_cabl";
    private static final String SIZE_DEL_CABL = "size_del_cabl";
    
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        /*AbstractFactoryDAO factoryDAO = FactoryCreator.getInstance().getFactory();
        ITaskDAO taskDAO = factoryDAO.createTaskDAO();
        List<Task> tasks = taskDAO.findByPerformerStatus(1003, "ACTIVE");
        List<Task> tasksDev = null;
        List<Task> tasksCabl = null;
        List<Task> tasksDelCabl = null;
        for (int i = 0; i != tasks.size(); i++) {
            if (tasks.get(i).getType().equals(Task.Type.NEW_DEVICE)) {
                tasksDev.add(tasks.get(i));
            }
            else if (tasks.get(i).getType().equals(Task.Type.NEW_CABLE)) {
                tasksCabl.add(tasks.get(i));
            }
            else if (tasks.get(i).getType().equals(Task.Type.DELETE_CABLE)){
                tasksDelCabl.add(tasks.get(i));
            }
        }
        request.setAttribute(TASKS_DEV, tasksDev);
        request.setAttribute(TASKS_CABL, tasksCabl);
        request.setAttribute(TASKS_DEL_CABL, tasksDelCabl);
        request.setAttribute(SIZE_DEV, tasksDev.size());
        request.setAttribute(SIZE_CABL, tasksCabl.size());
        request.setAttribute(SIZE_DEL_CABL, tasksDelCabl.size());
        if (tasks.isEmpty()){
            return "/index.jsp";
        }
        return "/IEdashboard.jsp";*/
        return DashboardsUtilities.getTaskUserStatus(1003, "ACTIVE");
    }
    
    
}
