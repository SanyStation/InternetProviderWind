/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.wind.commands.impl;

import com.netcracker.wind.commands.ICommand;
import com.netcracker.wind.dao.IUserDAO;
import com.netcracker.wind.dao.factory.DAOFactory;
import com.netcracker.wind.entities.User;
import com.netcracker.wind.manager.ConfigurationManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Anatolii
 */
public class Login implements ICommand {

    private static final String EMAIL = "email";
    private static final String PASSWORD = "password";
    private static final String USER = "user";

    public String execute(HttpServletRequest request, HttpServletResponse response) {

        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }

        IUserDAO userDAO = DAOFactory.createUserDAO();
        String email = request.getParameter(EMAIL);
        String password = request.getParameter(PASSWORD);
        User user = userDAO.findByEmailAndPassword(email, password);
        if (user == null) {
            return ConfigurationManager.getInstance().getProperty(ConfigurationManager.PAGE_LOGIN_ERROR);
        }

        session = request.getSession(true);
        session.setAttribute(USER, user);
        String rolePageKey = user.getRoles().getName().toUpperCase();

        return ConfigurationManager.getInstance().getProperty(rolePageKey);
    }

}
