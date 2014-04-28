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
import com.netcracker.wind.dao.interfaces.IUserDAO;
import com.netcracker.wind.entities.Task;
import com.netcracker.wind.entities.User;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Anatolii
 */
public class SelectTask implements ICommand {

    private static final String TASK_ID = "task_id";
    private static final String NAME = "name";

    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return "no session";
            //TODO redirect to login or register page
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
        IUserDAO userDAO = factoryDAO.createUserDAO();
        Object paramNameUser = session.getAttribute(NAME);
        if (paramNameUser == null) {
            //TODO redirect to login or register page
            return "no user";
        }
        String email = (String) paramNameUser;
        User user = userDAO.findByEmail(email);
        Task task = taskDAO.occupyTaskByID(taskId, user.getId());
        if (task == null) {
            //TODO set error message
            //TODO return error page with error message
            
        }
        
        //TODO return next page
        return "";
    }

}
