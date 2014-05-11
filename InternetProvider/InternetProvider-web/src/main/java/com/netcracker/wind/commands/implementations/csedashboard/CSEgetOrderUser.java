package com.netcracker.wind.commands.implementations.csedashboard;

import com.netcracker.wind.annotations.RolesAllowed;
import com.netcracker.wind.commands.ICommand;
import com.netcracker.wind.dao.implementations.helper.AbstractOracleDAO;
import com.netcracker.wind.entities.Role;
import com.netcracker.wind.entities.User;
import com.netcracker.wind.manager.ConfigurationManager;
import com.netcracker.wind.paging.IExtendedPaginatedList;
import com.netcracker.wind.paging.OrderUserPaginatedList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Oksana
 */
@RolesAllowed(roles = Role.Roles.CustomerSupportEngineer)
public class CSEgetOrderUser implements ICommand {

    private static final String ORDERS = "orders";

    public String execute(HttpServletRequest request, HttpServletResponse response) {

        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("user");
        int performerId = user.getId();
        IExtendedPaginatedList paginatedList = new OrderUserPaginatedList(request,
                AbstractOracleDAO.DEFAULT_PAGE_SIZE).setPerformer(performerId);
        session.setAttribute(ORDERS, paginatedList);
        return manager.getProperty(ConfigurationManager.PAGE_CSE_SERVICE_ORDERS);
    }
}
