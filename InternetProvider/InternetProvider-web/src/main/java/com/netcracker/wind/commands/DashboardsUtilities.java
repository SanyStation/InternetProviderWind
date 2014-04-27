/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.wind.commands;

import com.netcracker.wind.commands.implementations.csedashboard.CSEGetGroupTasks;
import com.netcracker.wind.dao.factory.AbstractFactoryDAO;
import com.netcracker.wind.dao.factory.FactoryCreator;
import com.netcracker.wind.dao.interfaces.ITaskDAO;
import com.netcracker.wind.entities.Task;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Anatolii
 */
public class DashboardsUtilities {
    public static String parseTaskJSON(List<Task> tasks){
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

    public static String getTaskJSON(int groupId) {
        AbstractFactoryDAO factoryDAO = FactoryCreator.getInstance().getFactory();
        ITaskDAO taskDAO = factoryDAO.createTaskDAO();
        List<Task> tasks = taskDAO.findByGroup(groupId);  
        return parseTaskJSON(tasks);
    }
    
    public static String getTaskUserJSON(int userId) {
        AbstractFactoryDAO factoryDAO = FactoryCreator.getInstance().getFactory();
        ITaskDAO taskDAO = factoryDAO.createTaskDAO();
        List<Task> tasks = taskDAO.findByGroup(userId);
        return parseTaskJSON(tasks);
    }
}
