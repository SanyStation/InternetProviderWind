package com.netcracker.wind.commands.implementations.iedashboard;

import com.netcracker.wind.annotations.RolesAllowed;
import com.netcracker.wind.commands.ICommand;
import com.netcracker.wind.dao.factory.AbstractFactoryDAO;
import com.netcracker.wind.dao.factory.FactoryCreator;
import com.netcracker.wind.dao.implementations.helper.AbstractOracleDAO;
import com.netcracker.wind.entities.Role;
import com.netcracker.wind.entities.reports.RiMostProfRouter;
import com.netcracker.wind.manager.ConfigurationManager;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Alexander Kovriga
 */
@RolesAllowed(roles = Role.Roles.InstallationEngineer)
public class IEgetReportRiProfit implements ICommand {

    public String execute(HttpServletRequest request,
            HttpServletResponse response) {
        AbstractFactoryDAO factoryDAO
                = FactoryCreator.getInstance().getFactory();
        List<RiMostProfRouter> devices
                = factoryDAO.createRiMostProfRouterDAO().findAll();
        HttpSession hs = request.getSession(false);
        hs.setAttribute("devices", devices);
        hs.setAttribute("title", "Routers' profitability");
        hs.setAttribute("pageSize", AbstractOracleDAO.DEFAULT_PAGE_SIZE);
        return manager.getProperty(ConfigurationManager.PAGE_IE_REPORT_RI_PROFIT);
    }

}
