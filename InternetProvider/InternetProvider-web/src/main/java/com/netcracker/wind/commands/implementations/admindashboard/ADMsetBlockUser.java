package com.netcracker.wind.commands.implementations.admindashboard;

import com.netcracker.wind.annotations.RolesAllowed;
import com.netcracker.wind.commands.ICommand;
import com.netcracker.wind.dao.factory.AbstractFactoryDAO;
import com.netcracker.wind.dao.factory.FactoryCreator;
import com.netcracker.wind.dao.interfaces.IUserDAO;
import com.netcracker.wind.entities.Role;
import com.netcracker.wind.entities.User;
import com.netcracker.wind.manager.ConfigurationManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This class-command allows block and unblock particular user. This command may
 * be calling only administrator. Also, administrator cannot block and unblock
 * himself and another administrators(if they be in system)
 *
 * @author Anatolii
 */
@RolesAllowed(roles = Role.Roles.Administrator)
public class ADMsetBlockUser implements ICommand {

    private static final String USER_ID = "user_id";
    private static final String USER_TO_VIEW = "us";

    public String execute(HttpServletRequest request, HttpServletResponse response) {
        int userId = Integer.parseInt(request.getParameter(USER_ID));
        AbstractFactoryDAO factoryDAO = FactoryCreator.getInstance().getFactory();
        IUserDAO userDAO = factoryDAO.createUserDAO();

        User user = userDAO.findById(userId);
        if (user != null && user.getRoleId() != Role.ADM_GROUP_ID) {
            user.setBlocked(!user.isBlocked());
            userDAO.update(user);
        }
        request.setAttribute(USER_TO_VIEW, user);
        return manager.getProperty(ConfigurationManager.PAGE_ADM_REVIEW_USER);
    }

}
