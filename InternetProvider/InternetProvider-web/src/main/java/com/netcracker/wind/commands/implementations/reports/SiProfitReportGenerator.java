package com.netcracker.wind.commands.implementations.reports;

import com.netcracker.wind.commands.ICommand;
import com.netcracker.wind.dao.factory.AbstractFactoryDAO;
import com.netcracker.wind.dao.factory.FactoryCreator;
import com.netcracker.wind.entities.reports.SiProfit;
import com.netcracker.wind.manager.ConfigurationManager;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Alexander Kovriga
 */
public class SiProfitReportGenerator implements ICommand {

    public String execute(HttpServletRequest request,
            HttpServletResponse response) {
        HttpSession hs = request.getSession(false);
        AbstractFactoryDAO factoryDAO =
                FactoryCreator.getInstance().getFactory();
        String dateTo = request.getParameter("vdByMonth");
        List<SiProfit> orders =
                factoryDAO.createSiProfByMonthDAO().findByDateTo(dateTo);
        hs.setAttribute("title", "Profit on " + dateTo);
        hs.setAttribute("profits", orders);
        return ConfigurationManager.getInstance().
                getProperty(ConfigurationManager.REPORT_SI_P);
    }
    
}
