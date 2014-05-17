package com.netcracker.wind.commands.implementations.pedashboard;

import com.netcracker.wind.annotations.RolesAllowed;
import com.netcracker.wind.commands.ICommand;
import com.netcracker.wind.dao.factory.FactoryCreator;
import com.netcracker.wind.dao.interfaces.IPortDAO;
import com.netcracker.wind.entities.Port;
import com.netcracker.wind.entities.Role;
import com.netcracker.wind.manager.ConfigurationManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This class-command allows PE to review the port that assigned on defined 
 * device.
 * 
 * @author Alexander Kovriga
 */
@RolesAllowed(roles = Role.Roles.ProvisioningEngineer)
public class PEreviewPort implements ICommand {

    private static final String ID = "id";

    public String execute(HttpServletRequest request,
            HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter(ID));
        IPortDAO portDAO
                = FactoryCreator.getInstance().getFactory().createPortDAO();
        Port port = portDAO.findById(id);
        if (port == null) {
            return manager.getProperty(ConfigurationManager.PAGE_ERROR);
        }
        request.setAttribute("port", port);
        return manager.getProperty(ConfigurationManager.PAGE_PE_REVIEW_PORT);
    }

}
