package com.netcracker.wind.commands.implementations.csedashboard;

import com.netcracker.wind.commands.ICommand;
import com.netcracker.wind.dao.implementations.helper.AbstractOracleDAO;
import com.netcracker.wind.paging.CSEOrdersPaginatedList;
import com.netcracker.wind.paging.IExtendedPaginatedList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Oksana
 */
public class CSEgetOrdersForUser implements ICommand {

    private static final String ORDERS = "orders";
    private static final String CUSTOMER_ID = "customer_id";

    public String execute(HttpServletRequest request, HttpServletResponse response) {
        int userId = Integer.parseInt(request.getParameter(CUSTOMER_ID));
        IExtendedPaginatedList paginatedList = new CSEOrdersPaginatedList(request,
                AbstractOracleDAO.DEFAULT_PAGE_SIZE).setUser(userId);
        HttpSession session = request.getSession(false);
        if (session == null) {
            return "";
        }
        session.setAttribute(ORDERS, paginatedList);
        return "/WEB-INF/cse/cse-page-service-orders.jsp";
    }
}
