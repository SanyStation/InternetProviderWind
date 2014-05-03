/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.wind.commands.implementations.csedashboard;

import com.netcracker.wind.commands.DashboardsUtilities;
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
         int number = Integer.parseInt(request.getParameter("size"));
         int from = Integer.parseInt(request.getParameter("from"));
       return DashboardsUtilities.getUserRoleJSON(CUSTOMER_GROUP_ID, number, from);
    }
}
