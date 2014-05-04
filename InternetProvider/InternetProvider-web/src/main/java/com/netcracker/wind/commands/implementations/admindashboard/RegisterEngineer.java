

package com.netcracker.wind.commands.implementations.admindashboard;

import com.netcracker.wind.commands.ICommand;
import com.netcracker.wind.dao.factory.AbstractFactoryDAO;
import com.netcracker.wind.dao.factory.FactoryCreator;
import com.netcracker.wind.dao.interfaces.IRoleDAO;
import com.netcracker.wind.dao.interfaces.IUserDAO;
import com.netcracker.wind.entities.Role;
import com.netcracker.wind.entities.User;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Anatolii
 */
public class RegisterEngineer implements ICommand {

    private static final String LOGIN = "login";
    private static final String E_MAIL = "e-mail";
    private static final String PASSWORD = "pass";
    private static final String ROLE = "ROLE";

    public String execute(HttpServletRequest request, HttpServletResponse response) {
        AbstractFactoryDAO factoryDAO = FactoryCreator.getInstance().getFactory();
        IRoleDAO roleDAO = factoryDAO.createRoleDAO();
        IUserDAO userDAO = factoryDAO.createUserDAO();

        int roleId = Integer.parseInt(request.getParameter(ROLE));
        
        Role role = roleDAO.findById(roleId);

        User user = new User();
        user.setName(request.getParameter(LOGIN));
        user.setEmail(request.getParameter(E_MAIL));
        user.setPassword(request.getParameter(PASSWORD));
        user.setRole(role);
        user.setBlocked(false);
        userDAO.add(user);

        //TODO return next page
        return "";
    }

}
