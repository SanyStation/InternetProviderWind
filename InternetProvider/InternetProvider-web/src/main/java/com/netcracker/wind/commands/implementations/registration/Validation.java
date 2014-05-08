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
        private static final String LOGIN = "login";
        private static final String EMAIL = "e-mail";
        private static final String PASSWORD = "pass";
        private static final String CONFPASS = "confpass";
        

    public String execute(HttpServletRequest request, HttpServletResponse response) {
       
        //Getting parameters from request
        HashMap<String, String> newUserData = new HashMap();
        StringBuilder returnedMessage = new StringBuilder("");
        newUserData.put(LOGIN, request.getParameter(LOGIN));
        newUserData.put(EMAIL, request.getParameter(EMAIL));
        newUserData.put(PASSWORD, request.getParameter(PASSWORD));
        newUserData.put(CONFPASS, request.getParameter(CONFPASS));
        
        //Validation
        Validator validator = new Validator(newUserData);
        returnedMessage.append(validator.validateInputData());
        returnedMessage.append(validator.validatePasswords());     
        returnedMessage.append(validator.validateLogin());
        returnedMessage.append(validator.validateEmail());       
        return returnedMessage.toString();
    }
    
}
