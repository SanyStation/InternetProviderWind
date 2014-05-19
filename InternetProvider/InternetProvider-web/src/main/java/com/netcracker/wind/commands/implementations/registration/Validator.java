package com.netcracker.wind.commands.implementations.registration;

import com.netcracker.wind.dao.factory.AbstractFactoryDAO;
import com.netcracker.wind.dao.factory.FactoryCreator;
import com.netcracker.wind.dao.interfaces.IUserDAO;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class allows to validate inputed data. Method getIsvalid returns 111 
 * if data is valid
 *
 * @author myshko
 */
public class Validator {
    private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-]{2,}(\\." +
              "[_A-Za-z0-9-]+)*@[A-Za-z0-9]{1,}(\\.[A-Za-z0-9]+)*" +
                                              "(\\.[A-Za-z]{2,})$";
    private static final String LOGIN_PATTERN = "[a-zA-Z]{1}[a-zA-Z0-9 ]{2,49}";
    private static final String PASSWORD_PATTERN = ".{6,50}";
    private static final String RETURN_LOGIN_MESSAGE = "Such login exists";
    private static final String RETURN_EMAIL_MESSAGE = "Such e-mail exists";
    private static final String RETURN_INPUT_LOGIN_MESSAGE = "Invalid format of login (latin, space, numbers allowed)";
    private static final String RETURN_INPUT_EMAIL_MESSAGE = "Invalid format of e-mail";
    private static final String RETURN_INPUT_PASSWORD_MESSAGE = "Invalid format of password(from 6 to 50 chars allowed)";
    private static final String LOGIN = "login";
    private static final String PASSWORD = "pass";
    private static final String CONFIRM_PASSWORD = "confpass";
    private static final String E_MAIL = "email";
    
    private HashMap<String,String> data;
    private int isvalid=0; // email = 010 // login = 100 // password = 001
    /**
     *  <pre>login=100</pre>
     *  <pre>email=010</pre>
     *  <pre>passw=001</pre>
     * 
     */
    public int getIsvalid() {
        return isvalid;
    }
    
    public Validator(HashMap<String,String> validationData){
        data=validationData;
    }

    //Validation: is Login unique?
    public String validateLogin() {
        Pattern p = Pattern.compile(LOGIN_PATTERN);
        Matcher m = p.matcher(data.get(LOGIN));
        if (!m.matches()) return RETURN_INPUT_LOGIN_MESSAGE;
//        AbstractFactoryDAO factoryDAO = FactoryCreator.getInstance().getFactory();
//        IUserDAO userDAO = factoryDAO.createUserDAO();
//        if (userDAO.hasLogin(data.get(LOGIN))) return RETURN_LOGIN_MESSAGE;
        isvalid+=100;
        return "";
    }

    //Validation: is E-mail unique?
    public String validateEmail() {
        Pattern p = Pattern.compile(EMAIL_PATTERN);
        Matcher m = p.matcher(data.get(E_MAIL));
        if (!m.matches()) return RETURN_INPUT_EMAIL_MESSAGE;
        AbstractFactoryDAO factoryDAO = FactoryCreator.getInstance().getFactory();
        IUserDAO userDAO = factoryDAO.createUserDAO();
        if (userDAO.hasEmail(data.get(E_MAIL))) return RETURN_EMAIL_MESSAGE;
        isvalid+=10;
        return "";
    }

    //Data format validation
//    String validateInputData() {
//        Pattern p = Pattern.compile(LOGIN_PATTERN);
//        Matcher m = p.matcher(data.get(LOGIN));
//        if (!m.matches()) return RETURN_INPUT_LOGIN_MESSAGE;
//        p = Pattern.compile(EMAIL_PATTERN);
//        m = p.matcher(data.get(E_MAIL));
//        if (!m.matches()) return RETURN_INPUT_EMAIL_MESSAGE;
//        p = Pattern.compile(PASSWORD_PATTERN);
//        m = p.matcher(data.get(PASSWORD));
//        if (!m.matches()) return RETURN_INPUT_PASSWORD_MESSAGE;
//        return "";
//    }

    //Passwords equals validation
    public String validatePasswords() {
        Pattern p = Pattern.compile(PASSWORD_PATTERN);
        Matcher m = p.matcher(data.get(PASSWORD));
        if (!m.matches()) return RETURN_INPUT_PASSWORD_MESSAGE;
        if (!data.get(PASSWORD).equals(data.get(CONFIRM_PASSWORD))) return "Unequal password fields";
        isvalid+=1;
        return "";
    }
}
