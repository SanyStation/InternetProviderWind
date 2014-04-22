/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.wind.commands.implementations.csedashboard;

import com.netcracker.wind.commands.ICommand;
import com.netcracker.wind.commands.implementations.order.RefreshService;
import com.netcracker.wind.dao.factory.AbstractFactoryDAO;
import com.netcracker.wind.dao.factory.FactoryCreator;
import com.netcracker.wind.dao.interfaces.IPriceDAO;
import com.netcracker.wind.dao.interfaces.IProviderLocationDAO;
import com.netcracker.wind.dao.interfaces.ITaskDAO;
import com.netcracker.wind.entities.Price;
import com.netcracker.wind.entities.Service;
import com.netcracker.wind.entities.Task;
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
public class CSEGetGroupTasks implements ICommand {

    private static final int CSE_GROUP_ID = 4;

    public String execute(HttpServletRequest request, HttpServletResponse response) {
        AbstractFactoryDAO factoryDAO = FactoryCreator.getInstance().getFactory();
        ITaskDAO taskDAO = factoryDAO.createTaskDAO();
        List<Task> tasks=taskDAO.findByGroup(CSE_GROUP_ID);
        
   


                JSONArray tasksJSONArray = new JSONArray();
                for (Task task : tasks) {
            try {
                JSONObject taskJSON = new JSONObject();
                taskJSON.put("id", task.getId());
                taskJSON.put("type", task.getType());
                taskJSON.put("status", task.getStatus());
                
                
                tasksJSONArray.put(taskJSON);
            } catch (JSONException ex) {
                Logger.getLogger(CSEGetGroupTasks.class.getName()).log(Level.SEVERE, null, ex);
            }
                }
        
                

          
            return tasksJSONArray.toString();
        }
        

    }

