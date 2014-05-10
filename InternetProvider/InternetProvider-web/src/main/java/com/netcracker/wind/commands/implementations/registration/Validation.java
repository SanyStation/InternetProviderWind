/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.wind.commands.implementations.registration;

import com.netcracker.wind.commands.ICommand;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author myshko
 */
public class Validation implements ICommand {

    private static final String LOGIN = "login";
    private static final String EMAIL = "email";
    private static final String PASSWORD = "pass";
    private static final String CONFPASS = "confpass";
    private static final String REGISTERED = "registered";

    public String execute(HttpServletRequest request, HttpServletResponse response) {

        JSONObject answer = new JSONObject();

        //Getting parameters from request
        HashMap<String, String> newUserData = new HashMap();
        //StringBuilder returnedMessage = new StringBuilder("");
        newUserData.put(LOGIN, request.getParameter(LOGIN));
        newUserData.put(EMAIL, request.getParameter(EMAIL));
        newUserData.put(PASSWORD, request.getParameter(PASSWORD));
        newUserData.put(CONFPASS, request.getParameter(CONFPASS));

        //Validation
        Validator validator = new Validator(newUserData);
        //returnedMessage.append(validator.validateInputData());
        try {
            answer.put(PASSWORD, validator.validatePasswords());
            answer.put(LOGIN, validator.validateLogin());
            answer.put(EMAIL, validator.validateEmail());
            if (validator.getIsvalid() == 111) {
                Registration register  = new Registration();
                register.execute(request, response);
                answer.put(REGISTERED, true);
            }
            return answer.toString();
        } catch (JSONException ex) {
            Logger.getLogger(Validation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }

}
