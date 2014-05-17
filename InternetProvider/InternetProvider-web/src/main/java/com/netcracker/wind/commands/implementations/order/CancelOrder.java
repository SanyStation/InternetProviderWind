package com.netcracker.wind.commands.implementations.order;

import com.netcracker.wind.annotations.RolesAllowed;
import com.netcracker.wind.commands.ICommand;
import com.netcracker.wind.dao.factory.AbstractFactoryDAO;
import com.netcracker.wind.dao.factory.FactoryCreator;
import com.netcracker.wind.dao.interfaces.IServiceOrderDAO;
import com.netcracker.wind.entities.Role;
import com.netcracker.wind.entities.ServiceOrder;
import com.netcracker.wind.entities.User;
import com.netcracker.wind.manager.ConfigurationManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This class-command allows to cancel particular Service Order. Service Order
 * can be canceled if Service Order with status 'ENTERING'. Customer user can
 * cancel only own Service Order. CSE can cancel Service Order for any customer
 * user.
 *
 * @author Anatolii
 */
@RolesAllowed(roles = {Role.Roles.CustomerSupportEngineer, Role.Roles.CustomerUser})
public class CancelOrder implements ICommand {

    private static final String ORDER_ID = "order_id";
    private static final String ORDER = "order";
    private static final String USER = "user";
    private final String page;

    /**
     * Constructor for creating exemplar of this command.
     *
     * @param page page where will be redirect after executing command.
     */
    public CancelOrder(String page) {
        this.page = page;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        int orderId = Integer.parseInt(request.getParameter(ORDER_ID));

        AbstractFactoryDAO factoryDAO = FactoryCreator.getInstance().getFactory();
        IServiceOrderDAO serviceOrderDAO = factoryDAO.createServiceOrderDAO();
        ServiceOrder order = serviceOrderDAO.findById(orderId);
        User user = (User) request.getSession(false).getAttribute(USER);
        if (order == null
                || !order.getStatus().equals(ServiceOrder.Status.ENTERING)
                || (user.getId() != order.getUserId() && user.getRoleId() != Role.CSE_GROUP_ID)) {
            return manager.getProperty(ConfigurationManager.PAGE_ERROR);
        }
        order.setStatus(ServiceOrder.Status.CANCELLED);
        serviceOrderDAO.update(order);
        request.setAttribute(ORDER, order);
        return page;
    }

}
