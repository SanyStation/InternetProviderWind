/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.netcracker.wind.commands.implementations.iedashboard;

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
public class getActiveTasks implements ICommand {

    private static final String TASKS = "tasks";
    private static final String SIZE = "size";
    
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        AbstractFactoryDAO factoryDAO = FactoryCreator.getInstance().getFactory();
        ITaskDAO taskDAO = factoryDAO.createTaskDAO();
        List<Task> tasks = taskDAO.findByPerformerStatus(1003, "ACTIVE");
        request.setAttribute(TASKS, tasks);
        request.setAttribute(SIZE, tasks.size());
        if (tasks.isEmpty()){
            return "/index.jsp";
        }
        return "/IEdashboard.jsp";
    }
    
    
}
