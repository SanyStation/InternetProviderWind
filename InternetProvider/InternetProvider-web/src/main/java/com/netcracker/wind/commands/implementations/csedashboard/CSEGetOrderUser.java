/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.wind.commands.implementations.csedashboard;

import com.netcracker.wind.commands.ICommand;
import com.netcracker.wind.entities.User;
import com.netcracker.wind.paging.IExtendedPaginatedList;
import com.netcracker.wind.paging.OrderUserPaginatedList;
import com.netcracker.wind.paging.SIUserPaginationList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Oksana
 */
public class CSEGetOrderUser implements ICommand {

    private static final String ORDERS = "orders";

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
        IExtendedPaginatedList paginatedList = new OrderUserPaginatedList(request,
                IExtendedPaginatedList.DEFAULT_PAGE_SIZE).setPerformer(performerId);
        session.setAttribute(ORDERS, paginatedList);
        return "/WEB-INF/cse/cse-page-service-orders.jsp";
    }
}
