package com.netcracker.wind.commands.implementations.admindashboard;

import com.netcracker.wind.annotations.RolesAllowed;
import com.netcracker.wind.commands.ICommand;
import com.netcracker.wind.commands.implementations.registration.Validator;
import com.netcracker.wind.dao.factory.FactoryCreator;
import com.netcracker.wind.dao.implementations.helper.AbstractOracleDAO;
import com.netcracker.wind.dao.interfaces.IUserDAO;
import com.netcracker.wind.entities.Role;
import com.netcracker.wind.entities.User;
import com.netcracker.wind.mail.FormatedMail;
import com.netcracker.wind.mail.MailSendler;
import com.netcracker.wind.manager.ConfigurationManager;
import com.netcracker.wind.workflow.generator.PasswordGenerator;
import java.util.ArrayList;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Alexander Kovriga
 */
@RolesAllowed(roles = Role.Roles.Administrator)
public class ADMaddUser implements ICommand {

    private final static String REGISTRATION_MESSAGE = "Boreas registration";
    private static final String USER_TO_VIEW = "us";

    public String execute(HttpServletRequest request,
            HttpServletResponse response) {

        String name = request.getParameter("name");
        String email = request.getParameter("email");
        //null
        int role_id = Integer.parseInt("0".concat((String) (request.getParameter("role_id"))));
        HashMap<String, String> newUserData = new HashMap();

        newUserData.put("login", name);
        newUserData.put("email", email);
//        newUserData.put("pass", pass);
//        newUserData.put("confpass", pass);
        //Validation
        Validator validator = new Validator(newUserData);
//        String validMessage = validator.validatePasswords();
        String message_email = validator.validateEmail();
        String message_name = validator.validateLogin();

        IUserDAO userDAO = FactoryCreator.getInstance().getFactory().createUserDAO();
//        User user = userDAO.findByEmail(email);
//        if (user != null || email.isEmpty() || !(role_id>1)) {
        if (validator.getIsvalid() != 110 || !(role_id > 1)) {
            request.setAttribute("messageemail", message_email);
            request.setAttribute("erroremail", "has-error");
            request.setAttribute("messagename", message_name);
            request.setAttribute("errorname", "has-error");
            if (!(role_id > 1)) {
                request.setAttribute("messagebottom", "Select user group!");
            }
            return ConfigurationManager.getInstance().
                    getProperty(ConfigurationManager.PAGE_ADM_ADD_USER);
        }
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setBlocked(false);
        user.setRoleId(role_id);
        user.setPassword(PasswordGenerator.generatePassword());
//        user.setRoleId(Role.CU_GROUP_ID);
        int id = userDAO.add(user);
        if (id == AbstractOracleDAO.WRONG_ID) {
            request.setAttribute("messagebottom", "Unknown error");
            return ConfigurationManager.getInstance().
                    getProperty(ConfigurationManager.PAGE_ADM_ADD_USER);
        }
        user.setId(id);
        request.setAttribute(USER_TO_VIEW, user);
        ArrayList<User> users = new ArrayList<User>(1);
        users.add(user);
        new MailSendler().sendEmail(users, REGISTRATION_MESSAGE,
                new FormatedMail().getUserRegistrationMassage(user));
        return manager.getProperty(ConfigurationManager.PAGE_ADM_REVIEW_USER);
    }

}
