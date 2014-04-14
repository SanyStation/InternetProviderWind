/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.netcracker.wind.commands.impl;

import com.netcracker.wind.commands.ICommand;
import com.netcracker.wind.dao.factory.DAOFactory;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Oksana
 */
public class ShowUser implements ICommand{

    public String execute(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("depts", DAOFactory.createUserDAO().findByRole(1));
        return "printUsers.jsp";    
    }
    
}
