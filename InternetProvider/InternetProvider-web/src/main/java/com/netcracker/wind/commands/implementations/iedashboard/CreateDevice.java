package com.netcracker.wind.commands.implementations.iedashboard;

import com.netcracker.wind.annotations.RolesAllowed;
import com.netcracker.wind.commands.ICommand;
import com.netcracker.wind.dao.factory.AbstractFactoryDAO;
import com.netcracker.wind.dao.factory.FactoryCreator;
import com.netcracker.wind.dao.interfaces.ICircuitDAO;
import com.netcracker.wind.dao.interfaces.IDeviceDAO;
import com.netcracker.wind.dao.interfaces.IPortDAO;
import com.netcracker.wind.dao.interfaces.IServiceLocationDAO;
import com.netcracker.wind.dao.interfaces.ITaskDAO;
import com.netcracker.wind.entities.Circuit;
import com.netcracker.wind.entities.Device;
import com.netcracker.wind.entities.Port;
import com.netcracker.wind.entities.Role;
import com.netcracker.wind.entities.ServiceLocation;
import com.netcracker.wind.entities.Task;
import com.netcracker.wind.manager.ConfigurationManager;
import com.netcracker.wind.workflow.Workflow;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * {@code CreateDevice) is class for completing task NEW_DEVICE by Installation <br>
 * engineer.
 *
 * @author Sashko
 */
@RolesAllowed(roles = Role.Roles.InstallationEngineer)
public class CreateDevice implements ICommand {

    private static final String NAME = "name";
    public final String ERROR_MESSAGE = "Name cannot be empty!";
    public final String ERROR = "error";
    private static final int PORT_N = 60;
    private static final String TASK_ID = "task_id";
    private static final String TASK = "task";

    public String execute(HttpServletRequest request, HttpServletResponse response) {
        int taskID = Integer.parseInt(request.getParameter(TASK_ID));
        String deviceName = request.getParameter(NAME);

        AbstractFactoryDAO factoryDAO = FactoryCreator.getInstance().getFactory();
        IDeviceDAO deviceDAO = factoryDAO.createDeviceDAO();
        IPortDAO portDAO = factoryDAO.createPortDAO();
        ITaskDAO taskDAO = factoryDAO.createTaskDAO();
        ICircuitDAO circuitDAO = factoryDAO.createCircuitDAO();

        Task task = taskDAO.findById(taskID);
        request.setAttribute(TASK, task);
        //if task isn't 'ACTIVE' don't do task
        if (!task.getStatus().equals(Task.Status.ACTIVE) || "".equals(deviceName)) {
            request.setAttribute(ERROR, ERROR_MESSAGE);
            return manager.getProperty(ConfigurationManager.PAGE_IE_SELECTED_TASK);
        }
        IServiceLocationDAO servLocDAO = factoryDAO.createServiceLocationDAO();
        ServiceLocation servLoc = new ServiceLocation();

        Device device = new Device();
        device.setName(deviceName);
        deviceDAO.add(device);
        servLocDAO.add(servLoc);

        Port port = new Port();
        port.setDeviceId(device.getId());

        for (int i = 0; i != PORT_N; i++) {
            port.setName(deviceName + ":port " + (i + 1));
            portDAO.add(port);
        }

        task.setStatus(Task.Status.COMPLETED);
        taskDAO.update(task);
        Workflow.createTaskForNewScnario(task.getServiceOrder());
        List<Circuit> circuits = circuitDAO.findByNullPort();
        for (Circuit nullPortCircuit : circuits) {
            Workflow.createTaskForNewScnario(nullPortCircuit.getServiceInstance().getServiceOrder());
        }
        return manager.getProperty(ConfigurationManager.PAGE_IE_SELECTED_TASK);
    }
}
