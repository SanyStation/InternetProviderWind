/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.netcracker.wind.commands.implementations.registration;

import com.netcracker.wind.dao.factory.AbstractFactoryDAO;
import com.netcracker.wind.dao.factory.FactoryCreator;
import com.netcracker.wind.dao.interfaces.IUserDAO;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author myshko
 */
class Validator {
    private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-]{2,}(\\." +
              "[_A-Za-z0-9-]+)*@[A-Za-z0-9]{2,}(\\.[A-Za-z0-9]+)*" +
                                              "(\\.[A-Za-z]{2,})$";
    private static final String LOGIN_PATTERN = "[a-zA-Z]{1}[a-zA-Z0-9]{2,14}";
    private static final String PASSWORD_PATTERN = "[a-zA-Z0-9]{6,16}";
    private static final String RETURN_LOGIN_MESSAGE = "Such login exists<br />";
    private static final String RETURN_EMAIL_MESSAGE = "Such e-mail exists<br />";
    private static final String RETURN_INPUT_LOGIN_MESSAGE = "Invalid format of login<br />";
    private static final String RETURN_INPUT_EMAIL_MESSAGE = "Invalid format of e-mail<br />";
    private static final String RETURN_INPUT_PASSWORD_MESSAGE = "Invalid format of password<br />";
    private static final String LOGIN = "login";
    private static final String PASSWORD = "pass";
    private static final String CONFIRM_PASSWORD = "confpass";
    private static final String E_MAIL = "e-mail";
    
    HashMap<String,String> data;
    
    Validator(HashMap<String,String> validationData){
        data=validationData;
    }

    //Validation: is Login unique?
    String validateLogin() {
        AbstractFactoryDAO factoryDAO = FactoryCreator.getInstance().getFactory();
        IUserDAO userDAO = factoryDAO.createUserDAO();
        if (userDAO.hasLogin(data.get(LOGIN))) return RETURN_LOGIN_MESSAGE;
        return "";
    }

    //Validation: is E-mail unique?
    String validateEmail() {
        AbstractFactoryDAO factoryDAO = FactoryCreator.getInstance().getFactory();
        IUserDAO userDAO = factoryDAO.createUserDAO();
        if (userDAO.hasEmail(data.get(E_MAIL))) return RETURN_EMAIL_MESSAGE;
        return "";
    }

    //Data format validation
    String validateInputData() {
        Pattern p = Pattern.compile(LOGIN_PATTERN);
        Matcher m = p.matcher(data.get(LOGIN));
        if (!m.matches()) return RETURN_INPUT_LOGIN_MESSAGE;
        p = Pattern.compile(EMAIL_PATTERN);
        m = p.matcher(data.get(E_MAIL));
        if (!m.matches()) return RETURN_INPUT_EMAIL_MESSAGE;
        p = Pattern.compile(PASSWORD_PATTERN);
        m = p.matcher(data.get(PASSWORD));
        if (!m.matches()) return RETURN_INPUT_PASSWORD_MESSAGE;
        return "";
    }

    //Passwords equals validation
    String validatePasswords() {
        if (!data.get(PASSWORD).equalsIgnoreCase(data.get(CONFIRM_PASSWORD))) return "Unequal password fields<br />";
        return "";
    }
}
