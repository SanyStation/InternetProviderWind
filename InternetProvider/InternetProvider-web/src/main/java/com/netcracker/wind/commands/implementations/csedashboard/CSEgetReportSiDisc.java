package com.netcracker.wind.commands.implementations.csedashboard;

import com.netcracker.wind.commands.ICommand;
import com.netcracker.wind.dao.factory.AbstractFactoryDAO;
import com.netcracker.wind.dao.factory.FactoryCreator;
import com.netcracker.wind.dao.implementations.helper.AbstractOracleDAO;
import com.netcracker.wind.entities.reports.SiOrder;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Alexander Kovriga
 */
public class CSEgetReportSiDisc implements ICommand {

    public String execute(HttpServletRequest request,
            HttpServletResponse response) {
        HttpSession hs = request.getSession(false);
        AbstractFactoryDAO factoryDAO =
                FactoryCreator.getInstance().getFactory();
        
        String dateFrom = request.getParameter("vdFrom");
        String dateTo = request.getParameter("vdTo");
        dateFrom = dateFrom == null ? "" : dateFrom;
        dateTo = dateTo == null ? "" : dateTo;
        List<SiOrder> orders = factoryDAO.createSiDiscOrdersDAO().
                findDateFromTo(dateFrom, dateTo);
        hs.setAttribute("dateFrom", dateFrom);
        hs.setAttribute("dateTo", dateTo);
        hs.setAttribute("command", "cse_get_report_si_disc");
        hs.setAttribute("pageSize", AbstractOracleDAO.DEFAULT_PAGE_SIZE);
        hs.setAttribute("title", "Disconnect orders per period: " + dateFrom
                + " - " + dateTo);
        hs.setAttribute("orders", orders);
        return "/WEB-INF/cse/cse-page-report-si-orders.jsp";
    }
    
}
