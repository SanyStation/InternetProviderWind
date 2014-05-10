/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.wind.commands.implementations.iedashboard;

import com.netcracker.wind.commands.DashboardsUtilities;
import com.netcracker.wind.commands.ICommand;
import com.netcracker.wind.dao.implementations.helper.AbstractOracleDAO;
import com.netcracker.wind.entities.Role;
import com.netcracker.wind.entities.Task;
import com.netcracker.wind.paging.IExtendedPaginatedList;
import com.netcracker.wind.paging.TaskPaginationList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Class will be deleted after getting tasks unification. Now it's copypaste
 * from CSE
 *
 * @author myshko
 */
@Deprecated
public class IEGetGroupTasks implements ICommand {

    private static final String TASKS = "tasks";

    public String execute(HttpServletRequest request, HttpServletResponse response) {
        IExtendedPaginatedList paginatedList = new TaskPaginationList(request,
                AbstractOracleDAO.DEFAULT_PAGE_SIZE).setGroup(Role.IE_GROUP_ID);
        HttpSession session = request.getSession(false);
        if (session == null) {
            return "";
        }
        session.setAttribute(TASKS, paginatedList);
        return "/WEB-INF/ie/?";

    }
}
