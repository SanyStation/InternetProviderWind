package com.netcracker.wind.commands.implementations.pedashboard;

import com.netcracker.wind.annotations.RolesAllowed;
import com.netcracker.wind.commands.ICommand;
import com.netcracker.wind.dao.factory.FactoryCreator;
import com.netcracker.wind.dao.interfaces.IPortDAO;
import com.netcracker.wind.entities.Port;
import com.netcracker.wind.entities.Role;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Alexander Kovriga
 */
@RolesAllowed(roles = Role.Roles.InstallationEngineer)
public class PEreviewPort implements ICommand {
    
    private static final String ID = "id";

    public String execute(HttpServletRequest request,
            HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter(ID));
        IPortDAO portDAO
                = FactoryCreator.getInstance().getFactory().createPortDAO();
        Port port = portDAO.findById(id);
        if (port == null) {
            return "";
        }
        request.setAttribute("port", port);
        return "/WEB-INF/pe/pe-page-review-port.jsp";
    }

}
