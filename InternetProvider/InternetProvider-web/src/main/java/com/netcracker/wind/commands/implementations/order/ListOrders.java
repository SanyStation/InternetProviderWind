package com.netcracker.wind.commands.implementations.order;

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
 * This class-command allows customer user to get own list of Service Instances.
 * List of Service Order will be put to request under defined key - "orders"
 *
 * @author Anatolii
 */
@RolesAllowed(roles = {Role.Roles.CustomerUser})
public class ListOrders implements ICommand {

    private static final String ORDERS = "orders";
    private static final String USER = "user";

    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute(USER);
        int performerId = user.getId();
        IExtendedPaginatedList paginatedList = new OrderUserPaginatedList(
                AbstractOracleDAO.DEFAULT_PAGE_SIZE).setPerformer(performerId);
        paginatedList.setRequest(request);
        session.setAttribute(ORDERS, paginatedList);
        return manager.getProperty(ConfigurationManager.PAGE_CU_PAGE_ORDERS_LIST);
    }
}
