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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author myshko
 */
public class Registration implements ICommand {

    public String execute(HttpServletRequest request, HttpServletResponse response) {
        final String login = "login";
        final String email = "email";
        final String pass = "pass";
        User user = new User();
        Role userRole = new Role(5);
        user.setName((String)request.getParameter(login));
        user.setEmail((String)request.getParameter(email));
        user.setPassword((String)request.getParameter(pass));
        user.setBlocked(false);
        user.setRole(userRole);
        AbstractFactoryDAO factoryDAO = FactoryCreator.getInstance().getFactory();
        IUserDAO userDAO = factoryDAO.createUserDAO();
        userDAO.add(user);
        return "/index.jsp";
    }
    
}
