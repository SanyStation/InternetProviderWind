/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.wind.commands.implementations.dashboards;

import com.netcracker.wind.commands.ICommand;
import com.netcracker.wind.dao.implementations.helper.AbstractOracleDAO;
import com.netcracker.wind.entities.User;
import com.netcracker.wind.paging.IExtendedPaginatedList;
import com.netcracker.wind.paging.OwnTasksPaginatedList;
import com.netcracker.wind.paging.TasksByPerformerStatusPaginatedList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Oksana
 */
public class GetTasksByPerformerStatus implements ICommand {

    private static final String TASKS = "tasks";
    private String pageForReturn;
    private String status;

    public GetTasksByPerformerStatus(String pageForReturn, String status) {
        this.pageForReturn = pageForReturn;
        this.status = status;
    }

    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return "";
        }
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "";
        }
        int performerId = user.getId();
        IExtendedPaginatedList paginatedList = new TasksByPerformerStatusPaginatedList(request,
                AbstractOracleDAO.DEFAULT_PAGE_SIZE).setPerformer(performerId).setStatus(status);
        session.setAttribute(TASKS, paginatedList);
        return pageForReturn;
    }
}
