package com.netcracker.wind.commands.implementations.admindashboard;

import com.netcracker.wind.commands.ICommand;
import com.netcracker.wind.dao.factory.AbstractFactoryDAO;
import com.netcracker.wind.dao.factory.FactoryCreator;
import com.netcracker.wind.dao.interfaces.IUserDAO;
import com.netcracker.wind.entities.User;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Anatolii
 */
public class BlockUser implements ICommand {

    private static final String USER_ID = "user_id";

    public String execute(HttpServletRequest request, HttpServletResponse response) {
        int userId = Integer.parseInt(request.getParameter(USER_ID));
        AbstractFactoryDAO factoryDAO = FactoryCreator.getInstance().getFactory();
        IUserDAO userDAO = factoryDAO.createUserDAO();

        User user = userDAO.findById(userId);
        if (user != null) {
            user.setBlocked(true);
            userDAO.update(user);
        }
        //TODO return next page
        return "";
    }

}
