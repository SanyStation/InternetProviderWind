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
    HashMap<String,String> data;
    
    Validator(HashMap<String,String> validationData){
        data=validationData;
    }

    //Validation: is Login unique?
    String validateLogin() {
        AbstractFactoryDAO factoryDAO = FactoryCreator.getInstance().getFactory();
        IUserDAO userDAO = factoryDAO.createUserDAO();
        if (userDAO.hasLogin(data.get("login"))) return "Such login exists";
        return "";
    }

    //Validation: is E-mail unique?
    String validateEmail() {
        AbstractFactoryDAO factoryDAO = FactoryCreator.getInstance().getFactory();
        IUserDAO userDAO = factoryDAO.createUserDAO();
        if (userDAO.hasEmail(data.get("email"))) return "Such e-mail exists";
        return "";
    }

    //Data format validation
    String validateInputData() {
        Pattern p = Pattern.compile(LOGIN_PATTERN);
        Matcher m = p.matcher(data.get("login"));
        if (!m.matches()) return "Invalid format of login\n";
        p = Pattern.compile(EMAIL_PATTERN);
        m = p.matcher(data.get("email"));
        if (!m.matches()) return "Invalid format of e-mail\n";
        p = Pattern.compile(PASSWORD_PATTERN);
        m = p.matcher(data.get("pass"));
        if (!m.matches()) return "Invalid format of password\n";
        return "";
    }

    //Passwords equals validation
    String validatePasswords() {
        if (!data.get("pass").equalsIgnoreCase(data.get("confpass"))) return "Unequal password fields";
        return "";
    }
}
