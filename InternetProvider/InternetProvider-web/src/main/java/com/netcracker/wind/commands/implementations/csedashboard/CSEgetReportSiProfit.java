package com.netcracker.wind.commands.implementations.csedashboard;

import com.netcracker.wind.annotations.RolesAllowed;
import com.netcracker.wind.commands.ICommand;
import com.netcracker.wind.dao.factory.AbstractFactoryDAO;
import com.netcracker.wind.dao.factory.FactoryCreator;
import com.netcracker.wind.dao.implementations.helper.AbstractOracleDAO;
import com.netcracker.wind.entities.Role;
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
@RolesAllowed(roles = Role.Roles.CustomerSupportEngineer)
public class CSEgetReportSiProfit implements ICommand {

    public String execute(HttpServletRequest request,
            HttpServletResponse response) {
        HttpSession hs = request.getSession(false);
        AbstractFactoryDAO factoryDAO =
                FactoryCreator.getInstance().getFactory();
        String date = request.getParameter("vdByMonth");
        date = date == null ? "" : date;
        List<SiProfit> orders =
                factoryDAO.createSiProfByMonthDAO().findByDateTo(date);
        hs.setAttribute("date", date);
        hs.setAttribute("pageSize", AbstractOracleDAO.DEFAULT_PAGE_SIZE);
        hs.setAttribute("title", "Profit on " + date);
        hs.setAttribute("profits", orders);
        return manager.getProperty(ConfigurationManager.PAGE_CSE_REPORT_SI_PROFIT);
    }
    
}
