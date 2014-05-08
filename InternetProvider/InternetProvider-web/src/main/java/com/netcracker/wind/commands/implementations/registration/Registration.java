/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.wind.commands.implementations.registration;

import com.netcracker.wind.commands.ICommand;
import com.netcracker.wind.dao.factory.AbstractFactoryDAO;
import com.netcracker.wind.dao.factory.FactoryCreator;
import com.netcracker.wind.dao.interfaces.IUserDAO;
import com.netcracker.wind.entities.Role;
import com.netcracker.wind.entities.User;
import com.netcracker.wind.mail.FormatedMail;
import com.netcracker.wind.mail.MailSendler;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author myshko
 */
public class Registration implements ICommand {

    private final static String REGISTRATION_MESSAGE = "Boreas registration";
    private final static String LOGIN = "login";
    private final static String EMAIL = "email";
    private final static String PASS = "pass";

    public String execute(HttpServletRequest request, HttpServletResponse response) {
        User user = new User();
        Role userRole = new Role(5);
        user.setName((String) request.getParameter(LOGIN));
        user.setEmail((String) request.getParameter(EMAIL));
        user.setPassword((String) request.getParameter(PASS));
        user.setBlocked(false);
        user.setRole(userRole);
        AbstractFactoryDAO factoryDAO = FactoryCreator.getInstance().getFactory();
        IUserDAO userDAO = factoryDAO.createUserDAO();
        userDAO.add(user);
        List<User> users = new ArrayList<User>();
        users.add(user);
        //new MailSendler().sendEmail(users, REGISTRATION_MESSAGE, new FormatedMail().getUserRegistrationMassage(user));
        return "/index.jsp";
    }

}
