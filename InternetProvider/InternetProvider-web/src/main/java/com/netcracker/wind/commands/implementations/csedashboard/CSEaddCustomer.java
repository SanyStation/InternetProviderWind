package com.netcracker.wind.commands.implementations.csedashboard;

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
 * @author j_mart
 */
@RolesAllowed(roles = Role.Roles.CustomerSupportEngineer)
public class CSEaddCustomer implements ICommand {

    private final static String REGISTRATION_MESSAGE = "Boreas registration";

    public String execute(HttpServletRequest request,
            HttpServletResponse response) {

        String name = request.getParameter("name");
        String email = request.getParameter("email");
        //String pass = request.getParameter("pass");

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
        IUserDAO userDAO
                = FactoryCreator.getInstance().getFactory().createUserDAO();
//        User user = userDAO.findByEmail(email);
//        if (user != null || email.isEmpty()) {
        if (validator.getIsvalid() != 110) {//magic numbers!!!
            request.setAttribute("messageemail", message_email);
            request.setAttribute("erroremail", "has-error");
            request.setAttribute("messagename", message_name);
            request.setAttribute("errorname", "has-error");
            return manager.getProperty(
                    ConfigurationManager.PAGE_CSE_CUSTOMER_REVIEW);
        }
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setBlocked(false);
        user.setPassword(PasswordGenerator.generatePassword());
        user.setRoleId(Role.CU_GROUP_ID);
        int id = userDAO.add(user);
        if (id == AbstractOracleDAO.WRONG_ID) {
            request.setAttribute("messageemail", "Unknown error");
            return manager.getProperty(
                    ConfigurationManager.PAGE_CSE_ADD_CUSTOMER);
        }
        user.setId(id);
        request.setAttribute("customer", user);
        ArrayList<User> users = new ArrayList<User>(1);
        users.add(user);
        new MailSendler().sendEmail(users, REGISTRATION_MESSAGE,
                new FormatedMail().getUserRegistrationMassage(user));
        return manager.getProperty(
                ConfigurationManager.PAGE_CSE_CUSTOMER_REVIEW);
    }

}
