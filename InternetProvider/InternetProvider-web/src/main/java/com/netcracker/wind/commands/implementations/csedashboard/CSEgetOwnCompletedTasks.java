/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.netcracker.wind.commands.implementations.csedashboard;

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
         AbstractFactoryDAO factoryDAO = FactoryCreator.getInstance().getFactory();
        ITaskDAO taskDAO = factoryDAO.createTaskDAO();
        User user =(User)request.getAttribute("user");
        List<Task> tasks = taskDAO.findByPerformerStatus(user.getId(),"completed");

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
