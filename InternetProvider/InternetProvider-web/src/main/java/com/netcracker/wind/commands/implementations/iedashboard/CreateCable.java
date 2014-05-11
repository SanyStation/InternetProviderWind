package com.netcracker.wind.commands.implementations.iedashboard;

import com.netcracker.wind.annotations.RolesAllowed;
import com.netcracker.wind.commands.ICommand;
import com.netcracker.wind.dao.factory.AbstractFactoryDAO;
import com.netcracker.wind.dao.factory.FactoryCreator;
import com.netcracker.wind.dao.interfaces.ICableDAO;
import com.netcracker.wind.dao.interfaces.IPortDAO;
import com.netcracker.wind.dao.interfaces.ITaskDAO;
import com.netcracker.wind.entities.Cable;
import com.netcracker.wind.entities.Circuit;
import com.netcracker.wind.entities.Role;
import com.netcracker.wind.entities.Task;
import com.netcracker.wind.manager.ConfigurationManager;
import com.netcracker.wind.workflow.Workflow;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author �����
 */
@RolesAllowed(roles = Role.Roles.InstallationEngineer)
public class CreateCable implements ICommand {

    public final String TASK_ID = "task_id";
    public final String ERROR_MESSAGE = "Name cannot be empty!";
    public final String ERROR = "error";
    public final String NAME = "name";

    public String execute(HttpServletRequest request, HttpServletResponse response) {
        int taskID = Integer.parseInt(request.getParameter(TASK_ID));
        AbstractFactoryDAO factoryDAO = FactoryCreator.getInstance().getFactory();
        ICableDAO cableDAO = factoryDAO.createCableDAO();
        IPortDAO portDAO = factoryDAO.createPortDAO();
        ITaskDAO taskDAO = factoryDAO.createTaskDAO();

        Task task = taskDAO.findById(taskID);
        request.setAttribute("task", task);
        String name = request.getParameter(NAME);
        if (!task.getStatus().equals(Task.Status.ACTIVE) || "".equals(name)) {
            request.setAttribute(ERROR, ERROR_MESSAGE);
            return manager.getProperty(ConfigurationManager.PAGE_IE_SELECTED_TASK);
        }

        Cable cable = new Cable();
        Circuit circuit = task.getServiceOrder().getServiceInstance().getCircuit();
        cable.setName(name);
        cable.setServiceLocationId(task.getServiceOrder().getServiceLocation().getId());
        cable.setPortId(portDAO.findById(circuit.getPortId()).getId());
        cableDAO.add(cable);
        task.setStatus(Task.Status.COMPLETED);
        taskDAO.update(task);
        Workflow.createTaskForPE(task.getServiceOrder(), Task.Type.CREATE_CIRCUIT, taskDAO);
        return manager.getProperty(ConfigurationManager.PAGE_IE_SELECTED_TASK);
    }

}
