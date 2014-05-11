package com.netcracker.wind.commands.implementations.dashboards;

import com.netcracker.wind.annotations.RolesAllowed;
import com.netcracker.wind.commands.ICommand;
import com.netcracker.wind.dao.factory.FactoryCreator;
import com.netcracker.wind.dao.interfaces.IServiceInstanceDAO;
import com.netcracker.wind.entities.Role;
import com.netcracker.wind.entities.ServiceInstance;
import com.netcracker.wind.entities.User;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Anatolii
 */
@RolesAllowed(roles = {Role.Roles.CustomerUser, Role.Roles.CustomerSupportEngineer})
public class InstanceReview implements ICommand {

    private static final String ID = "id";
    private static final String USER = "user";
    private static final String INSTANCE = "instance";

    private final String page;

    public InstanceReview(String page) {
        this.page = page;
    }

    public String execute(HttpServletRequest request, HttpServletResponse response) {
        int instanceId = Integer.parseInt(request.getParameter(ID));
        IServiceInstanceDAO serviceInstanceDAO = FactoryCreator.getInstance().getFactory().createServiceInstanceDAO();
        ServiceInstance serviceInstance = serviceInstanceDAO.findById(instanceId);
        User user = (User)request.getSession(false).getAttribute(USER);
        if (serviceInstance == null || (user.getRoleId() != Role.CSE_GROUP_ID 
                && serviceInstance.getUserId() != user.getId())) {
            return "";
        }
        request.setAttribute(INSTANCE, serviceInstance);
        return page;
    }

}
