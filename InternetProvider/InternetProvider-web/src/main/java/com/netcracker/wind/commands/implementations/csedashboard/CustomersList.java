/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.wind.commands.implementations.csedashboard;

import com.netcracker.wind.commands.ICommand;
import com.netcracker.wind.dao.factory.AbstractFactoryDAO;
import com.netcracker.wind.dao.factory.FactoryCreator;
import com.netcracker.wind.dao.interfaces.IUserDAO;
import com.netcracker.wind.entities.User;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Anna
 */
public class CustomersList implements ICommand {

    private static final int CUSTOMER_GROUP_ID = 5;

    public String execute(HttpServletRequest request,
            HttpServletResponse response) {
        AbstractFactoryDAO factoryDAO = FactoryCreator.getInstance().getFactory();
        IUserDAO userDAO = factoryDAO.createUserDAO();
        List<User> customers = userDAO.findByRole(CUSTOMER_GROUP_ID);
        JSONArray customersJSONArray = new JSONArray();
        for (User user : customers) {
            try {
                JSONObject userJSON = new JSONObject();
                userJSON.put("id", user.getId());
                userJSON.put("name", user.getName());
                userJSON.put("email", user.getEmail());

                customersJSONArray.put(userJSON);
            } catch (JSONException ex) {
                Logger.getLogger(CSEGetGroupTasks.class.getName())
                        .log(Level.ERROR, null, ex);
            }
        }

        return customersJSONArray.toString();
    }
}
