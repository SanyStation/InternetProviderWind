package com.netcracker.wind.commands.implementations.reports;

import com.netcracker.wind.commands.ICommand;
import com.netcracker.wind.dao.factory.AbstractFactoryDAO;
import com.netcracker.wind.dao.factory.FactoryCreator;
import com.netcracker.wind.entities.reports.RiRouterUtilNCap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Alexander Kovriga
 */
public class RiRoutersUtilNCapReportGenerator implements ICommand {

    public String execute(HttpServletRequest request, 
            HttpServletResponse response) {
        AbstractFactoryDAO factoryDAO =
                FactoryCreator.getInstance().getFactory();
        List<RiRouterUtilNCap> devices =
                factoryDAO.createRiRoutersUtilNCapDAO().findAll();
        HttpSession httpSession = request.getSession(false);
        httpSession.setAttribute("devices", devices);
        return "/RiRoutersUtilNCap.jsp";
    }
    
}
