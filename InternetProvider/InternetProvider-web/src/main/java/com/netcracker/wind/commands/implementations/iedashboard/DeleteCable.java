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
import com.netcracker.wind.dao.interfaces.IPortDAO;
import com.netcracker.wind.dao.interfaces.ITaskDAO;
import com.netcracker.wind.entities.Cable;
import com.netcracker.wind.entities.Port;
import com.netcracker.wind.entities.Task;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Сашко
 */
public class DeleteCable implements ICommand{
    
    public final String TASK_ID = "task_id";

    public String execute(HttpServletRequest request, HttpServletResponse response) {
        
        int taskID;
        try {
            taskID = Integer.parseInt(request.getParameter(TASK_ID));
        } catch (NumberFormatException exception) {
            return "";
        } 
        
        AbstractFactoryDAO factoryDAO = FactoryCreator.getInstance().getFactory();
        ICableDAO cableDAO = factoryDAO.createCableDAO();
        IPortDAO portDAO = factoryDAO.createPortDAO();
        ITaskDAO taskDAO = factoryDAO.createTaskDAO();
        
        Task task = taskDAO.findByID(taskID);
        Cable cable = cableDAO.findByServiceLocation(
                task.getServiceOrder().getProviderLocationId());
        
        Port port = cable.getPort();
        port.setFree(true);
        portDAO.update(port);
        cableDAO.delete(cable.getId());
        
        task.setStatus(Task.Status.COMPLETED);
        taskDAO.update(task);
        
        return "/index.jsp"; 
    }
    
}
