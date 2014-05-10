/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.wind.commands.implementations.order;

import com.netcracker.wind.annotations.RolesAllowed;
import com.netcracker.wind.commands.ICommand;
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
public class ReviewOrder implements ICommand {

    private static final String ORDER_ID = "order_id";
    private static final String ORDER = "order";
    private static final String USER = "user";
    private final String page;

    public ReviewOrder(String page) {
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
            //TODO return error page
            return "";
        }
        request.setAttribute(ORDER, order);
        return page;
    }
}
