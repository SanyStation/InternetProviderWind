package com.netcracker.wind.commands.implementations;

import com.netcracker.wind.commands.ICommand;
import com.netcracker.wind.dao.factory.AbstractFactoryDAO;
import com.netcracker.wind.dao.factory.FactoryCreator;
import com.netcracker.wind.entities.reports.RiRoutersUtilNCap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Alexander Kovriga
 */
public class RiRoutersUtilNCapReportGenerator implements ICommand {

    public String execute(
            HttpServletRequest request,
            HttpServletResponse response
    ) {
        AbstractFactoryDAO factoryDAO =
                FactoryCreator.getInstance().getFactory();
        List<RiRoutersUtilNCap> devices =
                factoryDAO.createRiRoutersUtilNCapDAO().find();
        HttpSession httpSession = request.getSession(false);
        httpSession.setAttribute("devices", devices);
        return "/RiRoutersUtilNCap.jsp";
    }
    
}
