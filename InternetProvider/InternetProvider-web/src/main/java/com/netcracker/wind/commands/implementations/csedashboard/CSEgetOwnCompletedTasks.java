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
public class CSEgetOwnCompletedTasks implements ICommand {

    public String execute(HttpServletRequest request, HttpServletResponse response) {
        // User user =(User)request.getAttribute("user");
         return DashboardsUtilities.getTaskUserStatus(1002,"completed");
    }
    
}
