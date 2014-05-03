package com.netcracker.wind.commands.implementations.csedashboard;

import com.netcracker.wind.commands.ICommand;
import com.netcracker.wind.dao.factory.AbstractFactoryDAO;
import com.netcracker.wind.dao.factory.FactoryCreator;
import com.netcracker.wind.dao.implementations.helper.AbstractOracleDAO;
import com.netcracker.wind.dao.interfaces.IServiceOrderDAO;
import com.netcracker.wind.entities.ServiceOrder;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Oksana
 */
public class CSEgetOrders implements ICommand {

    public String execute(HttpServletRequest request, HttpServletResponse response) {
        AbstractFactoryDAO factoryDAO = FactoryCreator.getInstance().getFactory();
        IServiceOrderDAO serviceOrderDAO = factoryDAO.createServiceOrderDAO();
        List<ServiceOrder> serviceOrders = serviceOrderDAO.findAll(
                AbstractOracleDAO.DEFAULT_PAGE_NUMBER,
                AbstractOracleDAO.DEFAULT_PAGE_SIZE);

        return "";
    }

}
