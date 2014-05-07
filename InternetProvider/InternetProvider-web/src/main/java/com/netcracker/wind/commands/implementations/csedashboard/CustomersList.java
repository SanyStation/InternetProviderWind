package com.netcracker.wind.commands.implementations.csedashboard;

import com.netcracker.wind.commands.ICommand;
import com.netcracker.wind.dao.implementations.helper.AbstractOracleDAO;
import com.netcracker.wind.paging.CSEUsersPaginatedList;
import com.netcracker.wind.paging.IExtendedPaginatedList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Anna
 */
public class CustomersList implements ICommand {

//    private static final int CUSTOMER_GROUP_ID = 5;
    private static final String USERS = "users";

    public String execute(HttpServletRequest request,
            HttpServletResponse response) {
        IExtendedPaginatedList paginatedList = new CSEUsersPaginatedList(request,
                AbstractOracleDAO.DEFAULT_PAGE_SIZE);
        HttpSession session = request.getSession(false);
        if (session == null) {
            return "";
        }
        session.setAttribute(USERS, paginatedList);
        return "/WEB-INF/cse/cse-page-users-list.jsp";
    }
}
