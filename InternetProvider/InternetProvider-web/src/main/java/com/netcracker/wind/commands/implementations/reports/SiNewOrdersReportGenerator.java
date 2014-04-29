package com.netcracker.wind.commands.implementations.reports;

import com.netcracker.wind.commands.ICommand;
import com.netcracker.wind.dao.factory.AbstractFactoryDAO;
import com.netcracker.wind.dao.factory.FactoryCreator;
import com.netcracker.wind.entities.reports.SiOrder;
import com.netcracker.wind.manager.ConfigurationManager;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Alexander Kovriga
 */
public class SiNewOrdersReportGenerator implements ICommand {

    public String execute(HttpServletRequest request,
            HttpServletResponse response) {
        HttpSession hs = request.getSession(false);
        AbstractFactoryDAO factoryDAO =
                FactoryCreator.getInstance().getFactory();
        String dateFrom = request.getParameter("vdNewFrom");
        String dateTo = request.getParameter("vdNewTo");
        List<SiOrder> orders = factoryDAO.createSiNewOrdersDAO().
                findDateFromTo(dateFrom, dateTo);
        hs.setAttribute("title", "New orders per period: " + dateFrom
                + " - " + dateTo);
        hs.setAttribute("orders", orders);
        return ConfigurationManager.getInstance().
                getProperty(ConfigurationManager.REPORT_SI_O);
    }
    
}
