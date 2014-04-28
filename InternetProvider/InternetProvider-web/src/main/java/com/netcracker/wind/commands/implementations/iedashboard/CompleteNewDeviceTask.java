/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.netcracker.wind.commands.implementations.iedashboard;

import com.netcracker.wind.commands.ICommand;
import com.netcracker.wind.dao.factory.AbstractFactoryDAO;
import com.netcracker.wind.dao.factory.FactoryCreator;
import com.netcracker.wind.dao.interfaces.IDeviceDAO;
import com.netcracker.wind.dao.interfaces.ITaskDAO;
import com.netcracker.wind.entities.Device;
import com.netcracker.wind.entities.Task;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Сашко
 */
public class CompleteNewDeviceTask implements ICommand {
    public static final String D_NAME = "d_name"; 
    public static final String TASK_ID = "d_name"; 

    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String dName;
        int  taskID;
        dName = request.getParameter(D_NAME);
        taskID = Integer.parseInt(request.getParameter(TASK_ID));
        AbstractFactoryDAO factoryDAO = FactoryCreator.getInstance().getFactory();
        IDeviceDAO deviceDAO = factoryDAO.createDeviceDAO();
        ITaskDAO taskDAO = factoryDAO.createTaskDAO();
        Task task = taskDAO.findByID(taskID);
        Device device = deviceDAO.findByName(dName);
        
        if (device == null){
            return "";
        }
        
        task.setStatus("COMPLETED");
        taskDAO.update(task);
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
