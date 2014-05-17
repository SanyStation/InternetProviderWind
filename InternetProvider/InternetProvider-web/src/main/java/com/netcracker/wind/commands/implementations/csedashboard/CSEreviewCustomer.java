package com.netcracker.wind.commands.implementations.csedashboard;

import com.netcracker.wind.annotations.RolesAllowed;
import com.netcracker.wind.commands.ICommand;
import com.netcracker.wind.dao.factory.FactoryCreator;
import com.netcracker.wind.dao.interfaces.IUserDAO;
import com.netcracker.wind.entities.Role;
import com.netcracker.wind.entities.User;
import com.netcracker.wind.manager.ConfigurationManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This class-command allows CSE to get information about particular customer
 * user. Needed customer user will be put to request under defined key -
 * "customer". Command may be invoke only CSE.
 *
 * @author Anatolii
 */
@RolesAllowed(roles = Role.Roles.CustomerSupportEngineer)
public class CSEreviewCustomer implements ICommand {

    private static final String ID = "id";
    private static final String CUSTOMER = "customer";

    public String execute(HttpServletRequest request, HttpServletResponse response) {
        int customerId = Integer.parseInt(request.getParameter(ID));
        IUserDAO userDAO = FactoryCreator.getInstance().getFactory().createUserDAO();
        User user = userDAO.findById(customerId);
        if (user == null || user.getRoleId() != Role.CU_GROUP_ID) {
            return manager.getProperty(ConfigurationManager.PAGE_ERROR);
        }
        request.setAttribute(CUSTOMER, user);
        return manager.getProperty(ConfigurationManager.PAGE_CSE_CUSTOMER_REVIEW);
    }

}
