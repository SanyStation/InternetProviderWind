package com.netcracker.wind.commands.implementations.csedashboard;

import com.netcracker.wind.commands.DashboardsUtilities;
import com.netcracker.wind.commands.ICommand;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 *
 * @author Anna
 */
public class CustomersList implements ICommand {

    private static final int CUSTOMER_GROUP_ID = 5;

    public String execute(HttpServletRequest request,
            HttpServletResponse response) {
         int number = Integer.parseInt(request.getParameter("size"));
         int from = Integer.parseInt(request.getParameter("from"));
       return DashboardsUtilities.getUserRoleJSON(CUSTOMER_GROUP_ID, number,
               from);
    }
}
