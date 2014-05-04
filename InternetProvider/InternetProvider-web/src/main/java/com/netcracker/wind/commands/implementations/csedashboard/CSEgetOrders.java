package com.netcracker.wind.commands.implementations.csedashboard;

import com.netcracker.wind.commands.ICommand;
import com.netcracker.wind.dao.factory.AbstractFactoryDAO;
import com.netcracker.wind.dao.factory.FactoryCreator;
import com.netcracker.wind.dao.implementations.helper.AbstractOracleDAO;
import com.netcracker.wind.dao.interfaces.IServiceOrderDAO;
import com.netcracker.wind.entities.ServiceOrder;
import com.netcracker.wind.paging.CSEOrdersPaginatedList;
import com.netcracker.wind.paging.IExtendedPaginatedList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Oksana
 */
public class CSEgetOrders implements ICommand {

    private static final String ORDERS = "orders";

    public String execute(HttpServletRequest request, HttpServletResponse response) {
         IExtendedPaginatedList paginatedList = new CSEOrdersPaginatedList(request, 
                IExtendedPaginatedList.DEFAULT_PAGE_SIZE);
        HttpSession session = request.getSession(false);
        if(session == null){
            return "";
        }
        session.setAttribute(ORDERS, paginatedList);
        return "/WEB-INF/cse/?.jsp";
    }
}
