/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.wind.commands.implementations.csedashboard;

import com.netcracker.wind.commands.ICommand;
import com.netcracker.wind.entities.Role;
import com.netcracker.wind.paging.IExtendedPaginatedList;
import com.netcracker.wind.paging.TaskPaginationList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * don't need any more
 *
 * @author Oksana
 */
public class CSEGetGroupTasks implements ICommand {

    private static final String TASKS = "tasks";

    public String execute(HttpServletRequest request, HttpServletResponse response) {
        IExtendedPaginatedList paginatedList = new TaskPaginationList(request,
                IExtendedPaginatedList.DEFAULT_PAGE_SIZE).setGroup(Role.CSE_GROUP_ID);
        HttpSession session = request.getSession(false);
        if (session == null) {
            return "";
        }
        session.setAttribute(TASKS, paginatedList);
        return "/WEB-INF/cse/cse-page-tasks-list.jsp";

    }

}
