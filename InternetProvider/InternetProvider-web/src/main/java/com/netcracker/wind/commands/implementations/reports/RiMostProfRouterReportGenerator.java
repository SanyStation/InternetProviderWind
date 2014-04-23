package com.netcracker.wind.commands.implementations.reports;

import com.netcracker.wind.commands.ICommand;
import com.netcracker.wind.dao.factory.AbstractFactoryDAO;
import com.netcracker.wind.dao.factory.FactoryCreator;
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
public class RiMostProfRouterReportGenerator implements ICommand {

    public String execute(HttpServletRequest request,
            HttpServletResponse response) {
        AbstractFactoryDAO factoryDAO
                = FactoryCreator.getInstance().getFactory();
        List<RiMostProfRouter> devices
                = factoryDAO.createRiMostProfRouterDAO().findAll();
        HttpSession httpSession = request.getSession(false);
        httpSession.setAttribute("devices", devices);
        return ConfigurationManager.getInstance().getProperty(ConfigurationManager.REPORT_RIM);
    }

}
