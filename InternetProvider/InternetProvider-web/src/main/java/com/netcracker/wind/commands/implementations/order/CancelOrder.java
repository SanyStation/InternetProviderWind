package com.netcracker.wind.commands.implementations.order;

import com.netcracker.wind.annotations.RolesAllowed;
import com.netcracker.wind.commands.ICommand;
import com.netcracker.wind.dao.factory.AbstractFactoryDAO;
import com.netcracker.wind.dao.factory.FactoryCreator;
import com.netcracker.wind.dao.interfaces.IServiceOrderDAO;
import com.netcracker.wind.entities.Role;
import com.netcracker.wind.entities.ServiceOrder;
import com.netcracker.wind.entities.User;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Anatolii
 */
@RolesAllowed(roles = {Role.Roles.CustomerSupportEngineer, Role.Roles.CustomerUser})
public class CancelOrder implements ICommand {

    private static final String ORDER_ID = "order_id";
    private static final String ORDER = "order";
    private static final String USER = "user";
    private final String page;

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
            //TODO return error page
            return "";
        }
        order.setStatus(ServiceOrder.Status.CANCELLED);
        serviceOrderDAO.update(order);
        request.setAttribute(ORDER, order);
        //TODO redirect to next page
        return page;
    }

}
