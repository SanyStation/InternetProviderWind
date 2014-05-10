package com.netcracker.wind.commands.implementations.pedashboard;

import com.netcracker.wind.annotations.RolesAllowed;
import com.netcracker.wind.commands.ICommand;
import com.netcracker.wind.dao.factory.AbstractFactoryDAO;
import com.netcracker.wind.dao.factory.FactoryCreator;
import com.netcracker.wind.dao.implementations.helper.AbstractOracleDAO;
import com.netcracker.wind.entities.Role;
import com.netcracker.wind.entities.reports.CiaIpt;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Alexander Kovriga
 */
@RolesAllowed(roles = Role.Roles.InstallationEngineer)
public class PEgetReportCiaIpt implements ICommand {

    public String execute(HttpServletRequest request,
            HttpServletResponse response) {
        AbstractFactoryDAO factoryDAO
                = FactoryCreator.getInstance().getFactory();
        List<CiaIpt> links = factoryDAO.createCiaIptDAO().findAll();
        HttpSession hs = request.getSession(false);
        hs.setAttribute("links", links);
        hs.setAttribute("title", "Impact Propagation Tree");
        hs.setAttribute("pageSize", AbstractOracleDAO.DEFAULT_PAGE_SIZE);
        return "/WEB-INF/pe/pe-page-report-cia-ipt.jsp";
    }

}
