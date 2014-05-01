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
import com.netcracker.wind.dao.interfaces.ICircuitDAO;
import com.netcracker.wind.dao.interfaces.IPortDAO;
import com.netcracker.wind.dao.interfaces.IServiceInstanceDAO;
import com.netcracker.wind.dao.interfaces.IServiceLocationDAO;
import com.netcracker.wind.dao.interfaces.ITaskDAO;
import com.netcracker.wind.entities.Cable;
import com.netcracker.wind.entities.Circuit;
import com.netcracker.wind.entities.ServiceInstance;
import com.netcracker.wind.entities.Task;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Сашко
 */
public class CreateCable implements ICommand {
    
    public final String TASK_ID = "task_id";

    public String execute(HttpServletRequest request, HttpServletResponse response) {
        int taskID = -1;
        try {
            taskID = Integer.parseInt(request.getParameter(TASK_ID));
        } catch (NumberFormatException exception) {
            return "";
        } 
        if (taskID == -1) {
            return "/IEdashboard.jsp";
        }
        AbstractFactoryDAO factoryDAO = FactoryCreator.getInstance().getFactory();
        ICableDAO cableDAO = factoryDAO.createCableDAO();
        IServiceLocationDAO serviceLocationDAO = factoryDAO.createServiceLocationDAO();
        IPortDAO portDAO  = factoryDAO.createPortDAO();
        ITaskDAO taskDAO = factoryDAO.createTaskDAO();
        IServiceInstanceDAO serviceInstanceDAO = factoryDAO.createServiceInstanceDAO();
        ICircuitDAO circuitDAO = factoryDAO.createCircuitDAO();
        
        Cable cable = new Cable();
        
        Task task = taskDAO.findByID(taskID);
        //ServiceInstance serviceInstance = serviceInstanceDAO.findByID(1);
        
        /*task.getServiceOrder().getId()
        Circuit circuit = circuitDAO.findByServInst(serviceInstance.getId());
        cable.setServiceLocation(
                serviceLocationDAO.findByID(task.getServiceOrder().getServiceLocation().getId()));
        cable.setPort(portDAO.findByID(circuit.getPort().getId()));
        cableDAO.add(cable);
        task.setStatus(Task.TaskStatus.COMPLETED.toString());
        taskDAO.update(task);*/
        return "/index.jsp";
    }
    
}
