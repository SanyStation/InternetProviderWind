package com.netcracker.wind.commands.implementations.pedashboard;

import com.netcracker.wind.annotations.RolesAllowed;
import com.netcracker.wind.commands.ICommand;
import com.netcracker.wind.dao.factory.FactoryCreator;
import com.netcracker.wind.dao.interfaces.IServiceInstanceDAO;
import com.netcracker.wind.entities.Role;
import com.netcracker.wind.entities.ServiceInstance;
import com.netcracker.wind.manager.ConfigurationManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Alexander Kovriga
 */
@RolesAllowed(roles = Role.Roles.ProvisioningEngineer)
public class PEreviewSI implements ICommand {
    
    private static final String ID = "id";

    public String execute(HttpServletRequest request,
            HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter(ID));
        IServiceInstanceDAO siDAO
                = FactoryCreator.getInstance().getFactory().
                        createServiceInstanceDAO();
        ServiceInstance si = siDAO.findById(id);
        if (si == null) {
            return "";
        }
        request.setAttribute("instance", si);
        return manager.getProperty(ConfigurationManager.PAGE_PE_REVIEW_INSTANCE);
    }

}
