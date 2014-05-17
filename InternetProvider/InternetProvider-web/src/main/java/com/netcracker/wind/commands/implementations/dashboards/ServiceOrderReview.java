package com.netcracker.wind.commands.implementations.dashboards;

import com.netcracker.wind.annotations.RolesAllowed;
import com.netcracker.wind.commands.ICommand;
import com.netcracker.wind.dao.factory.FactoryCreator;
import com.netcracker.wind.dao.interfaces.IServiceOrderDAO;
import com.netcracker.wind.entities.Role;
import com.netcracker.wind.entities.ServiceOrder;
import com.netcracker.wind.entities.User;
import com.netcracker.wind.manager.ConfigurationManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This class-command allows to get information about Service Order. Command
 * can be invoke customer user to review own Service Order and CSE to review
 * Service Instance for customer user. Needed Service Order will be putted
 * into request under defined key - "order"
 *
 * @author Anatolii
 */
@RolesAllowed(roles = {Role.Roles.CustomerSupportEngineer, Role.Roles.CustomerUser})
public class ServiceOrderReview implements ICommand {

    private static final String ORDER_ID = "order_id";
    private static final String ORDER = "order";
    private static final String USER = "user";
    private final String page;

    /**
     * Constructor for creating exemplar of this command.
     *
     * @param page page where will be redirected after executing command.
     */
    public ServiceOrderReview(String page) {
        this.page = page;
    }

    public String execute(HttpServletRequest request, HttpServletResponse response) {
        int orderId = Integer.parseInt(request.getParameter(ORDER_ID));
        IServiceOrderDAO orderDAO
                = FactoryCreator.getInstance().getFactory().createServiceOrderDAO();
        ServiceOrder order = orderDAO.findById(orderId);
        User user = (User) request.getSession(false).getAttribute(USER);
        if (order == null
                || (user.getId() != order.getUserId() && user.getRoleId() != Role.CSE_GROUP_ID)) {
            return manager.getProperty(ConfigurationManager.PAGE_ERROR);
        }
        request.setAttribute(ORDER, order);
        return page;
    }
}
