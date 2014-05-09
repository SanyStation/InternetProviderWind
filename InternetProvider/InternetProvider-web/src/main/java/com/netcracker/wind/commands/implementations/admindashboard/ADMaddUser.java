package com.netcracker.wind.commands.implementations.admindashboard;

import com.netcracker.wind.commands.ICommand;
import com.netcracker.wind.dao.factory.FactoryCreator;
import com.netcracker.wind.dao.implementations.helper.AbstractOracleDAO;
import com.netcracker.wind.dao.interfaces.IUserDAO;
import com.netcracker.wind.entities.User;
import com.netcracker.wind.workflow.generator.PasswordGenerator;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Alexander Kovriga
 */
public class ADMaddUser implements ICommand {

    private static final String USER_TO_VIEW = "us";

    public String execute(HttpServletRequest request,
            HttpServletResponse response) {

        String name = request.getParameter("name");
        String email = request.getParameter("email");

        IUserDAO userDAO = FactoryCreator.getInstance().getFactory().createUserDAO();
        User user = userDAO.findByEmail(email);
        if (user != null || email.isEmpty()) {
            return "/WEB-INF/admim/adm-page-add-user.jsp";
        }
        user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setBlocked(false);
        user.setPassword(PasswordGenerator.generatePassword());
//        user.setRoleId(Role.CU_GROUP_ID);
        int id = userDAO.add(user);
        if (id == AbstractOracleDAO.WRONG_ID) {
            return "/WEB-INF/admim/adm-page-add-user.jsp";
        }
        user.setId(id);
        request.setAttribute(USER_TO_VIEW, user);
        return "/WEB-INF/admin/adm-page-review-user.jsp";
    }

}
