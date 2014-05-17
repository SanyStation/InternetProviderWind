package com.netcracker.wind.commands.implementations.admindashboard;

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
 * This class-command allows administrator to get information about particular
 * user. Needed user will be put to request under defined key - "us". Command
 * may be invoke only Administrator.
 *
 * @author Anatolii
 */
@RolesAllowed(roles = Role.Roles.Administrator)
public class ADMreviewUser implements ICommand {

    private static final String ID = "id";
    private static final String USER = "us";

    public String execute(HttpServletRequest request, HttpServletResponse response) {
        int customerId = Integer.parseInt(request.getParameter(ID));
        IUserDAO userDAO
                = FactoryCreator.getInstance().getFactory().createUserDAO();
        User user = userDAO.findById(customerId);
        request.setAttribute(USER, user);
        return manager.getProperty(ConfigurationManager.PAGE_ADM_REVIEW_USER);
    }

}
