package com.netcracker.wind.commands.implementations;

import com.netcracker.wind.commands.ICommand;
import com.netcracker.wind.dao.factory.AbstractFactoryDAO;
import com.netcracker.wind.dao.factory.FactoryCreator;
import com.netcracker.wind.entities.reports.SiProfit;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Alexander Kovriga
 */
public class SiProfByMonthReportGenerator implements ICommand {

    public String execute(HttpServletRequest request,
            HttpServletResponse response) {
        HttpSession hs = request.getSession(false);
        AbstractFactoryDAO factoryDAO =
                FactoryCreator.getInstance().getFactory();
        String dateFrom = "2014-06-01";
        String dateTo = "2014-06-30";
        List<SiProfit> orders = factoryDAO.createSiProfByMonthDAO().
                findByDateFromTo(dateFrom, dateTo);
        hs.setAttribute("title", "Profit by month: " + dateFrom + " - "
                + dateTo);
        hs.setAttribute("profits", orders);
        return "/SiProfit.jsp";
    }
    
}
