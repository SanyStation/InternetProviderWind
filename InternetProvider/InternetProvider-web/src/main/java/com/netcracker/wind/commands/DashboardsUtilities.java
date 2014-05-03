package com.netcracker.wind.commands;

import com.netcracker.wind.commands.implementations.csedashboard.CSEGetGroupTasks;
import com.netcracker.wind.dao.factory.AbstractFactoryDAO;
import com.netcracker.wind.dao.factory.FactoryCreator;
import com.netcracker.wind.dao.implementations.helper.AbstractOracleDAO;
import com.netcracker.wind.dao.interfaces.ITaskDAO;
import com.netcracker.wind.dao.interfaces.IUserDAO;
import com.netcracker.wind.entities.Task;
import com.netcracker.wind.entities.User;
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

    public static String parseUserJSON(List<User> users, int size){
        JSONObject json = new JSONObject();
        try {
           
            json.put("size", size);
            JSONArray tasksJSONArray = new JSONArray();
            for (User user : users) {
                try {
                    JSONObject taskJSON = new JSONObject();
                    taskJSON.put("id", user.getId());
                    taskJSON.put("name", user.getName());
                    taskJSON.put("email", user.getEmail());
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
     
       public static String getUserRoleJSON(int roleId,int from,int number) {
        AbstractFactoryDAO factoryDAO = FactoryCreator.getInstance().getFactory();
        IUserDAO userDAO = factoryDAO.createUserDAO();
        List<User> users = userDAO.findByRole(roleId, from, number);
        return parseUserJSON(users,userDAO.findByRole(roleId,
                AbstractOracleDAO.DEFAULT_PAGE_NUMBER,
                AbstractOracleDAO.ALL_RECORDS).size());
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
        return parseTaskJSON(tasks,taskDAO.findByPerformer(userId,
                AbstractOracleDAO.DEFAULT_PAGE_NUMBER,
                AbstractOracleDAO.ALL_RECORDS).size());
    }
    public static String getTaskUserStatus(int userId, String status) {
        AbstractFactoryDAO factoryDAO = FactoryCreator.getInstance().getFactory();
        ITaskDAO taskDAO = factoryDAO.createTaskDAO();
        List<Task> tasks = taskDAO.findByPerformerStatus(userId, status,
                AbstractOracleDAO.DEFAULT_PAGE_NUMBER,
                AbstractOracleDAO.ALL_RECORDS);
        return parseTaskJSON(tasks,tasks.size());
    }
    
        public static String getTaskUserStatus(int userId, String status,int from,int number) {
        AbstractFactoryDAO factoryDAO = FactoryCreator.getInstance().getFactory();
        ITaskDAO taskDAO = factoryDAO.createTaskDAO();
        List<Task> tasks = taskDAO.findByPerformerStatus(userId, status,from,number);
        return parseTaskJSON(tasks,taskDAO.findByPerformerStatus(userId, status,
                AbstractOracleDAO.DEFAULT_PAGE_NUMBER,
                AbstractOracleDAO.ALL_RECORDS).size());
    }
}
