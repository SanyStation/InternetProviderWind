package com.netcracker.wind.commands.implementations.dashboards;

import com.netcracker.wind.annotations.RolesAllowed;
import com.netcracker.wind.commands.ICommand;
import com.netcracker.wind.dao.factory.FactoryCreator;
import com.netcracker.wind.dao.interfaces.IServiceInstanceDAO;
import com.netcracker.wind.entities.Role;
import com.netcracker.wind.entities.ServiceInstance;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Anatolii
 */
@RolesAllowed(roles = {Role.Roles.CustomerUser, Role.Roles.CustomerSupportEngineer})
public class InstanceReview implements ICommand {

    private static final String ID = "id";

    private final String page;

    public InstanceReview(String page) {
        this.page = page;
    }

    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String parameter = request.getParameter(ID);
        if (parameter == null) {
            return "";
        }
        int instanceId = Integer.parseInt(parameter);
        IServiceInstanceDAO serviceInstanceDAO = FactoryCreator.getInstance().getFactory().createServiceInstanceDAO();
        ServiceInstance serviceInstance = serviceInstanceDAO.findById(instanceId);
        if (serviceInstance == null) {
            return "";
        }
        request.setAttribute("instance", serviceInstance);
        return page;
    }

}
