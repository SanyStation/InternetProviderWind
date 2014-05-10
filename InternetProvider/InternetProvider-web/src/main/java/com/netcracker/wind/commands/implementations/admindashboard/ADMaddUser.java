package com.netcracker.wind.commands.implementations.admindashboard;

import com.netcracker.wind.annotations.RolesAllowed;
import com.netcracker.wind.commands.ICommand;
import com.netcracker.wind.dao.factory.FactoryCreator;
import com.netcracker.wind.dao.implementations.helper.AbstractOracleDAO;
import com.netcracker.wind.dao.interfaces.IUserDAO;
import com.netcracker.wind.entities.Role;
import com.netcracker.wind.entities.User;
import com.netcracker.wind.workflow.generator.PasswordGenerator;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Alexander Kovriga
 */
@RolesAllowed(roles = Role.Roles.Administrator)
public class ADMaddUser implements ICommand {

    private static final String USER_TO_VIEW = "us";

    public String execute(HttpServletRequest request,
            HttpServletResponse response) {

        String name = request.getParameter("name");
        String email = request.getParameter("email");
        int role_id = Integer.parseInt(request.getParameter("role_id"));

        IUserDAO userDAO = FactoryCreator.getInstance().getFactory().createUserDAO();
        User user = userDAO.findByEmail(email);
        if (user != null || email.isEmpty() || !(role_id>1)) {
            return "/WEB-INF/admim/adm-page-add-user.jsp";
        }
        user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setBlocked(false);
        user.setRoleId(role_id);
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
