package com.netcracker.wind.commands.implementations.iedashboard;

import com.netcracker.wind.annotations.RolesAllowed;
import com.netcracker.wind.commands.ICommand;
import com.netcracker.wind.dao.factory.AbstractFactoryDAO;
import com.netcracker.wind.dao.factory.FactoryCreator;
import com.netcracker.wind.dao.interfaces.ICableDAO;
import com.netcracker.wind.dao.interfaces.IPortDAO;
import com.netcracker.wind.dao.interfaces.ITaskDAO;
import com.netcracker.wind.entities.Cable;
import com.netcracker.wind.entities.Port;
import com.netcracker.wind.entities.Role;
import com.netcracker.wind.entities.Task;
import com.netcracker.wind.manager.ConfigurationManager;
import com.netcracker.wind.workflow.Workflow;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * {@code CreateCable) is class for completing task DELETE_CABLE by Installation <br>
 * engineer.
 * 
 * @author Sashko
 */
@RolesAllowed(roles = Role.Roles.InstallationEngineer)
public class DeleteCable implements ICommand {

    public static final String TASK_ID = "task_id";
    public static final String TASK = "task";

    public String execute(HttpServletRequest request, HttpServletResponse response) {

        int taskID = Integer.parseInt(request.getParameter(TASK_ID));

        AbstractFactoryDAO factoryDAO = FactoryCreator.getInstance().getFactory();
        ICableDAO cableDAO = factoryDAO.createCableDAO();
        IPortDAO portDAO = factoryDAO.createPortDAO();
        ITaskDAO taskDAO = factoryDAO.createTaskDAO();

        Task task = taskDAO.findById(taskID);
        request.setAttribute(TASK, task);
        if (!task.getStatus().equals(Task.Status.ACTIVE)) {
            return manager.getProperty(ConfigurationManager.PAGE_IE_SELECTED_TASK);
        }
        Cable cable = task.getServiceOrder().getServiceLocation().getCable();
        Port port = cable.getPort();
        port.setFree(true);
        portDAO.update(port);
        cableDAO.delete(cable.getId());
        task.setStatus(Task.Status.COMPLETED);
        taskDAO.update(task);
        Workflow.createTaskForCSE(task.getServiceOrder(), taskDAO);
        return manager.getProperty(ConfigurationManager.PAGE_IE_SELECTED_TASK);
    }

}
