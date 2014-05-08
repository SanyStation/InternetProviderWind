package com.netcracker.wind.commands.implementations.order;

import com.netcracker.wind.commands.ICommand;
import com.netcracker.wind.dao.factory.AbstractFactoryDAO;
import com.netcracker.wind.dao.factory.FactoryCreator;
import com.netcracker.wind.dao.interfaces.IServiceOrderDAO;
import com.netcracker.wind.entities.ServiceOrder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Anatolii
 */
public class CancelOrder implements ICommand {

    private static final String ORDER = "order_id";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String sOrderId = request.getParameter(ORDER);
        int orderId = Integer.parseInt(sOrderId);

        AbstractFactoryDAO factoryDAO = FactoryCreator.getInstance().getFactory();
        IServiceOrderDAO serviceOrderDAO = factoryDAO.createServiceOrderDAO();
        ServiceOrder order = serviceOrderDAO.findById(orderId);
        if (order == null || !order.getStatus().equals(ServiceOrder.Status.ENTERING)) {
            //TODO return error page
            return "";
        }
        order.setStatus(ServiceOrder.Status.CANCELLED);
        serviceOrderDAO.update(order);
        //TODO redirect to next page
        return "/WEB-INF/user/cu-orders-list.jsp";
    }

}
