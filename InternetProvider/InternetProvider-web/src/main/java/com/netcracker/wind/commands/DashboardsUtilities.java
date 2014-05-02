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

    public static String parseTaskJSON(List<Task> tasks,int size) {
         JSONObject json = new JSONObject();
        try {
           
            json.put("size", size);
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
            json.put("data", tasksJSONArray);
           
        } catch (JSONException ex) {
            Logger.getLogger(DashboardsUtilities.class.getName()).log(Level.SEVERE, null, ex);
        }
        return json.toString();
    }

    public static String getTaskJSON(int groupId) {
        AbstractFactoryDAO factoryDAO = FactoryCreator.getInstance().getFactory();
        ITaskDAO taskDAO = factoryDAO.createTaskDAO();
        List<Task> tasks = taskDAO.findByGroup(groupId);
        return parseTaskJSON(tasks,tasks.size());
    }

     public static String getGroupTaskJSON(int groupId,int from,int number) {
        AbstractFactoryDAO factoryDAO = FactoryCreator.getInstance().getFactory();
        ITaskDAO taskDAO = factoryDAO.createTaskDAO();
        List<Task> tasks = taskDAO.findByGroup(groupId, from, number);
        return parseTaskJSON(tasks,taskDAO.findByGroup(groupId).size());
    }
    public static String getTaskUserJSON(int userId) {
        AbstractFactoryDAO factoryDAO = FactoryCreator.getInstance().getFactory();
        ITaskDAO taskDAO = factoryDAO.createTaskDAO();
        List<Task> tasks = taskDAO.findByGroup(userId);
        return parseTaskJSON(tasks,tasks.size());
    }

        public static String getTaskUserJSON(int userId,int from,int number) {
        AbstractFactoryDAO factoryDAO = FactoryCreator.getInstance().getFactory();
        ITaskDAO taskDAO = factoryDAO.createTaskDAO();
        List<Task> tasks = taskDAO.findByPerformer(userId, from, number);
        return parseTaskJSON(tasks,taskDAO.findByPerformer(userId).size());
    }
    public static String getTaskUserStatus(int userId, String status) {
        AbstractFactoryDAO factoryDAO = FactoryCreator.getInstance().getFactory();
        ITaskDAO taskDAO = factoryDAO.createTaskDAO();
        List<Task> tasks = taskDAO.findByPerformerStatus(userId, status);
        return parseTaskJSON(tasks,tasks.size());
    }
    
        public static String getTaskUserStatus(int userId, String status,int from,int number) {
        AbstractFactoryDAO factoryDAO = FactoryCreator.getInstance().getFactory();
        ITaskDAO taskDAO = factoryDAO.createTaskDAO();
        List<Task> tasks = taskDAO.findByPerformerStatus(userId, status,from,number);
        return parseTaskJSON(tasks,taskDAO.findByPerformerStatus(userId, status).size());
    }
}
