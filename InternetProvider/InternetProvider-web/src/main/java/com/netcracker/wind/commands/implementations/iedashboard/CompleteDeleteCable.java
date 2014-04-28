/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.netcracker.wind.commands.implementations.iedashboard;

import com.netcracker.wind.commands.ICommand;
import com.netcracker.wind.dao.factory.AbstractFactoryDAO;
import com.netcracker.wind.dao.factory.FactoryCreator;
import com.netcracker.wind.dao.interfaces.ICableDAO;
import com.netcracker.wind.dao.interfaces.ITaskDAO;
import com.netcracker.wind.entities.Cable;
import com.netcracker.wind.entities.Task;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Сашко
 */
public class CompleteDeleteCable implements ICommand {
    
    public static final String CABLE_ID = "cable_id";
    public static final String TASK_ID = "task_id";
    
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        int cableID = Integer.parseInt(request.getParameter(CABLE_ID));
        int taskID = Integer.parseInt(request.getParameter(TASK_ID));
        AbstractFactoryDAO factoryDAO = FactoryCreator.getInstance().getFactory();
        ITaskDAO taskDAO = factoryDAO.createTaskDAO();
        ICableDAO cableDAO = factoryDAO.createCableDAO();
        Cable cable = cableDAO.findByID(cableID);
        Task task = taskDAO.findByID(taskID);
        
        if (cable != null) {
            return "";
        }
        
        task.setStatus("COMPLETED");
        taskDAO.update(task);
        
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
