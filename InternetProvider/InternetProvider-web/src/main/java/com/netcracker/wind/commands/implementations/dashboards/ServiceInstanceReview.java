package com.netcracker.wind.commands.implementations.dashboards;

import com.netcracker.wind.annotations.RolesAllowed;
import com.netcracker.wind.commands.ICommand;
import com.netcracker.wind.dao.factory.FactoryCreator;
import com.netcracker.wind.dao.interfaces.IServiceInstanceDAO;
import com.netcracker.wind.entities.Role;
import com.netcracker.wind.entities.ServiceInstance;
import com.netcracker.wind.entities.User;
import com.netcracker.wind.manager.ConfigurationManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This class-command allows to get information about Service Instance. Command can
 * be invoke customer user to review own Service Instance and CSE to review
 * Service Instance for customer user. Needed Service Instance will be putted
 * into request under defined key - "instance"
 *
 * @author Anatolii
 */
@RolesAllowed(roles = {Role.Roles.CustomerUser, Role.Roles.CustomerSupportEngineer})
public class ServiceInstanceReview implements ICommand {

    private static final String ID = "id";
    private static final String USER = "user";
    private static final String INSTANCE = "instance";

    private final String page;

    /**
     * Constructor for creating exemplar of this command.
     *
     * @param page page where will be redirected after executing command.
     */
    public ServiceInstanceReview(String page) {
        this.page = page;
    }

    public String execute(HttpServletRequest request, HttpServletResponse response) {
        int instanceId = Integer.parseInt(request.getParameter(ID));
        IServiceInstanceDAO serviceInstanceDAO = FactoryCreator.getInstance().getFactory().createServiceInstanceDAO();
        ServiceInstance serviceInstance = serviceInstanceDAO.findById(instanceId);
        User user = (User) request.getSession(false).getAttribute(USER);
        if (serviceInstance == null || (user.getRoleId() != Role.CSE_GROUP_ID
                && serviceInstance.getUserId() != user.getId())) {
            return manager.getProperty(ConfigurationManager.PAGE_ERROR);
        }
        request.setAttribute(INSTANCE, serviceInstance);
        return page;
    }

}
