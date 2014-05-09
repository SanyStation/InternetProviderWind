/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.wind.commands.implementations.dashboards;

import com.netcracker.wind.commands.ICommand;
import com.netcracker.wind.dao.factory.FactoryCreator;
import com.netcracker.wind.dao.interfaces.IUserDAO;
import com.netcracker.wind.entities.User;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Anatolii
 */
public class ChangePassword implements ICommand {

    private static final String PASSWORD = "password";
    private static final String CONF_PASSWORD = "conf_password";
    private static final String USER = "user";
    private static final String ANSWER = "answer";

    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String pass = request.getParameter(PASSWORD);
        String confPass = request.getParameter(CONF_PASSWORD);
        JSONObject answer = new JSONObject();
        if ("".equals(pass) || !pass.equals(confPass)) {
            try {
                answer.put(ANSWER, false);
            } catch (JSONException ex) {
                Logger.getLogger(ChangePassword.class.getName()).log(Level.SEVERE, null, ex);
            }
            return answer.toString();
        }

        User user = (User) request.getSession(false).getAttribute(USER);
        IUserDAO userDAO = FactoryCreator.getInstance().getFactory().createUserDAO();
        user.setPassword(pass);
        int result = userDAO.updatePass(user);
        try {
            if (result == 1) {
                answer.put(ANSWER, true);
            } else {
                answer.put(ANSWER, false);
            }
        } catch (JSONException ex) {
            Logger.getLogger(ChangePassword.class.getName()).log(Level.SEVERE, null, ex);
        }
        return answer.toString();
    }

}
