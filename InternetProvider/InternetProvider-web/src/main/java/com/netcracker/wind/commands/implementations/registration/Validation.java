/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.netcracker.wind.commands.implementations.registration;

import com.netcracker.wind.commands.ICommand;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author myshko
 */
public class Validation implements ICommand {

    public String execute(HttpServletRequest request, HttpServletResponse response) {
        final String login = "login";
        final String email = "e-mail";
        final String password = "pass";
        final String confpass = "confpass";
        
        //Getting parameters from request
        HashMap<String, String> newUserData = new HashMap();
        StringBuilder returnedMessage = new StringBuilder("");
        newUserData.put(login, request.getParameter(login));
        newUserData.put(email, request.getParameter(email));
        newUserData.put(password, request.getParameter(password));
        newUserData.put(confpass, request.getParameter(confpass));
        
        //Validation
        Validator validator = new Validator(newUserData);
        returnedMessage.append(validator.validateInputData());
        returnedMessage.append(validator.validatePasswords());     
        returnedMessage.append(validator.validateLogin());
        returnedMessage.append(validator.validateEmail());       
        return returnedMessage.toString();
    }
    
}
