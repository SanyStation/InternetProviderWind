/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.wind.commands.implementations.order;

import com.netcracker.wind.annotations.RolesAllowed;
import com.netcracker.wind.commands.ICommand;
import com.netcracker.wind.dao.implementations.helper.AbstractOracleDAO;
import com.netcracker.wind.entities.Role;
import com.netcracker.wind.entities.User;
import com.netcracker.wind.paging.IExtendedPaginatedList;
import com.netcracker.wind.paging.OrderUserPaginatedList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Anatolii
 */
@RolesAllowed(roles = {Role.Roles.CustomerSupportEngineer, Role.Roles.CustomerUser})
public class ListOrders implements ICommand {

    private static final String ORDERS = "orders";
    private static final String USER = "user";

    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute(USER);
        int performerId = user.getId();
        IExtendedPaginatedList paginatedList = new OrderUserPaginatedList(request,
                AbstractOracleDAO.DEFAULT_PAGE_SIZE).setPerformer(performerId);
        session.setAttribute(ORDERS, paginatedList);
        return "/WEB-INF/user/cu-page-orders-list.jsp";
    }
}
