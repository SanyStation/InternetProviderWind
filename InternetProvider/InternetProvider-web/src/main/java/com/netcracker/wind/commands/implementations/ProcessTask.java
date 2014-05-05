/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.wind.commands.implementations;

import com.netcracker.wind.commands.ICommand;
import com.netcracker.wind.dao.factory.FactoryCreator;
import com.netcracker.wind.dao.interfaces.ITaskDAO;
import com.netcracker.wind.entities.Role;
import com.netcracker.wind.entities.Task;
import com.netcracker.wind.entities.User;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Anatolii
 */
public class ProcessTask implements ICommand {

    private static final String TASK_ID = "task_id";
    private static final String USER = "user";
    private static final String TASK = "task";

    public String execute(HttpServletRequest request, HttpServletResponse response) {
        int taskId = Integer.parseInt(request.getParameter(TASK_ID));
        ITaskDAO taskDAO = FactoryCreator.getInstance().getFactory().createTaskDAO();
        Task task = taskDAO.findById(taskId);
        if (task == null) {
            return "";
        }

        User user = (User) request.getSession(false).getAttribute(USER);
        String page = "";
        request.setAttribute(TASK, task);

        switch (task.getStatus()) {
            case NEW:
                task.setStatus(Task.Status.ACTIVE);
                task.setUser(user);
                taskDAO.update(task);

                if (user.getRoleId() == Role.CSE_GROUP_ID) {
                    page = "/WEB-INF/cse/cse-page-selected-task.jsp";
                } else if (user.getRoleId() == Role.PE_GROUP_ID) {
                    page = "/WEB-INF/pe/pe-page-selected-task.jsp";
                } else if (user.getRoleId() == Role.IE_GROUP_ID) {
                    page = "/WEB-INF/ie/ie-page-selected-task.jsp";
                }
                break;
            case ACTIVE:
                if (user.getRoleId() == Role.CSE_GROUP_ID) {
                    page = "/WEB-INF/cse/cse-page-selected-task.jsp";
                } else if (user.getRoleId() == Role.PE_GROUP_ID) {
                    page = "/WEB-INF/pe/pe-page-selected-task.jsp";
                } else if (user.getRoleId() == Role.IE_GROUP_ID) {
                    page = "/WEB-INF/ie/ie-page-selected-task.jsp";
                }
                break;
            case COMPLETED:
                if (user.getRoleId() == Role.CSE_GROUP_ID) {
                    page = "/WEB-INF/cse/cse-page-selected-task.jsp";
                } else if (user.getRoleId() == Role.PE_GROUP_ID) {
                    page = "/WEB-INF/pe/pe-page-selected-task.jsp";
                } else if (user.getRoleId() == Role.IE_GROUP_ID) {
                    page = "/WEB-INF/ie/ie-page-selected-task.jsp";
                }
                break;
        }
        return page;
    }

}
