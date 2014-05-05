/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.netcracker.wind.commands.implementations.csedashboard;

import com.netcracker.wind.commands.ICommand;
import com.netcracker.wind.paging.IExtendedPaginatedList;
import com.netcracker.wind.paging.ServicePaginationList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Anna
 */
public class CSEgetService implements ICommand{
     private static final String SERVICES = "services";

    public String execute(HttpServletRequest request, HttpServletResponse response) {
        IExtendedPaginatedList paginatedList = new ServicePaginationList(request,
                IExtendedPaginatedList.DEFAULT_PAGE_SIZE);
        HttpSession session = request.getSession(false);
        if (session == null) {
            return "";
        }
        session.setAttribute(SERVICES, paginatedList);
        return "/WEB-INF/cse/?.jsp";
    }
}
