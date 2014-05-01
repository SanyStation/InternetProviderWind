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
import com.netcracker.wind.dao.interfaces.ITaskDAO;
import com.netcracker.wind.entities.Task;
import com.netcracker.wind.entities.User;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Oksana
 */
public class CSEgetOwnTasks implements ICommand {

    public String execute(HttpServletRequest request, HttpServletResponse response) {

       // int user = (Integer) request.getAttribute("user");
        // AbstractFactoryDAO factoryDAO = FactoryCreator.getInstance().getFactory();
        // factoryDAO.createUserDAO().findByID(user);
        int number = Integer.parseInt(request.getParameter("size"));
        int from = Integer.parseInt(request.getParameter("from"));
        return DashboardsUtilities.getTaskUserJSON(1002, from, number);
    }

}
