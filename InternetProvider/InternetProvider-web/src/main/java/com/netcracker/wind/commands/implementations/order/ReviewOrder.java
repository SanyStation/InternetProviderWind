/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.wind.commands.implementations.order;

import com.netcracker.wind.commands.ICommand;
import com.netcracker.wind.dao.factory.FactoryCreator;
import com.netcracker.wind.dao.interfaces.IServiceOrderDAO;
import com.netcracker.wind.entities.ServiceOrder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Anatolii
 */
public class ReviewOrder implements ICommand {

    private static final String ORDER_ID = "order_id";
    private static final String ORDER = "order";

    public String execute(HttpServletRequest request, HttpServletResponse response) {
        int orderId = Integer.parseInt(request.getParameter(ORDER_ID));
        IServiceOrderDAO orderDAO
                = FactoryCreator.getInstance().getFactory().createServiceOrderDAO();
        ServiceOrder order = orderDAO.findById(orderId);
        request.setAttribute(ORDER, order);
        return "/WEB-INF/user/cu-review-order.jsp";
    }

}
