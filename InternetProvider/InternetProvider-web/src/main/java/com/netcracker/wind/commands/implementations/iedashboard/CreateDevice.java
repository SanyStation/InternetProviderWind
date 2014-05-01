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
import com.netcracker.wind.dao.interfaces.IPortDAO;
import com.netcracker.wind.dao.interfaces.IServiceLocationDAO;
import com.netcracker.wind.dao.interfaces.ITaskDAO;
import com.netcracker.wind.entities.Device;
import com.netcracker.wind.entities.Port;
import com.netcracker.wind.entities.ServiceLocation;
import com.netcracker.wind.entities.Task;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Сашко
 */
public class CreateDevice implements ICommand {
    
    public static final String D_NAME = "d_name"; 
    public static final int PORT_N = 60;
    public static final String TASK_ID = "task_id";

    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String dName;
        int taskID = -1;
        dName = request.getParameter(D_NAME);
        try {
            taskID = Integer.parseInt(request.getParameter(TASK_ID));
        } catch (NumberFormatException exception) {
            //TODO log
            //TODO redirecct to error page
            return "";
        }
        if (taskID == -1) {
            return "";//error
        } 
        AbstractFactoryDAO factoryDAO = FactoryCreator.getInstance().getFactory();
        IDeviceDAO deviceDAO = factoryDAO.createDeviceDAO();
        IPortDAO portDAO = factoryDAO.createPortDAO();
        ITaskDAO taskDAO = factoryDAO.createTaskDAO();
        Task task = taskDAO.findByID(taskID);
        IServiceLocationDAO servLocDAO = factoryDAO.createServiceLocationDAO();
        ServiceLocation servLoc = new ServiceLocation();
        
        Device device = new Device();
        device.setName(dName);
        deviceDAO.add(device);
        servLocDAO.add(servLoc);

        Port port = new Port();
        port.setDevice(deviceDAO.findByID(device.getId()));
        
        for (int i = 0; i != PORT_N; i++){
            portDAO.add(port);
        }
        
        task.setStatus(Task.Status.COMPLETED);
        taskDAO.update(task); 
        
        return "/index.jsp";
    }
}
