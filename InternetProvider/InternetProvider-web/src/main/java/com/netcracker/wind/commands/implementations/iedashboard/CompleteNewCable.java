package com.netcracker.wind.commands.implementations.iedashboard;

import com.netcracker.wind.commands.ICommand;
import com.netcracker.wind.dao.factory.AbstractFactoryDAO;
import com.netcracker.wind.dao.factory.FactoryCreator;
import com.netcracker.wind.dao.interfaces.ICableDAO;
import com.netcracker.wind.dao.interfaces.IServiceOrderDAO;
import com.netcracker.wind.dao.interfaces.ITaskDAO;
import com.netcracker.wind.entities.Cable;
import com.netcracker.wind.entities.ServiceOrder;
import com.netcracker.wind.entities.Task;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author �����
 */
public class CompleteNewCable implements ICommand{
    public static final String TASK_ID = "d_name"; 
    public final String PORT_ID = "port_id";

    public String execute(HttpServletRequest request, HttpServletResponse response) {
        int  taskID;
        int portID;
        taskID = Integer.parseInt(request.getParameter(TASK_ID));
        portID = Integer.parseInt(request.getParameter(PORT_ID));
        AbstractFactoryDAO factoryDAO = FactoryCreator.getInstance().getFactory();
        ITaskDAO taskDAO = factoryDAO.createTaskDAO();
        IServiceOrderDAO serviceOrderDAO = factoryDAO.createServiceOrderDAO();
        ICableDAO cableDAO = factoryDAO.createCableDAO();
        Task task = taskDAO.findByID(taskID);
        ServiceOrder serviceOrder = task.getServiceOrder();
        Cable cable = cableDAO.findByPort(portID);
        
        if (cable == null || cable.getServiceLocation() == serviceOrder.getServiceLocation()){
            return "";
        }
        
        task.setStatus(Task.Status.COMPLETED);
        taskDAO.update(task);
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
