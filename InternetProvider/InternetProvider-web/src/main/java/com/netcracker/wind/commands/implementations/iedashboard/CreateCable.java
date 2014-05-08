package com.netcracker.wind.commands.implementations.iedashboard;

import com.netcracker.wind.commands.ICommand;
import com.netcracker.wind.dao.factory.AbstractFactoryDAO;
import com.netcracker.wind.dao.factory.FactoryCreator;
import com.netcracker.wind.dao.interfaces.ICableDAO;
import com.netcracker.wind.dao.interfaces.ICircuitDAO;
import com.netcracker.wind.dao.interfaces.IPortDAO;
import com.netcracker.wind.dao.interfaces.IServiceInstanceDAO;
import com.netcracker.wind.dao.interfaces.ITaskDAO;
import com.netcracker.wind.entities.Cable;
import com.netcracker.wind.entities.Circuit;
import com.netcracker.wind.entities.Task;
import com.netcracker.wind.workflow.Workflow;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author �����
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
            return "";
            //error
        }
        AbstractFactoryDAO factoryDAO = FactoryCreator.getInstance().getFactory();
        ICableDAO cableDAO = factoryDAO.createCableDAO();
        IPortDAO portDAO = factoryDAO.createPortDAO();
        ITaskDAO taskDAO = factoryDAO.createTaskDAO();
        IServiceInstanceDAO serviceInstanceDAO = factoryDAO.createServiceInstanceDAO();
        ICircuitDAO circuitDAO = factoryDAO.createCircuitDAO();

        Cable cable = new Cable();

        Task task = taskDAO.findById(taskID);

        Circuit circuit = task.getServiceOrder().getServiceInstance().getCircuit();
        cable.setServiceLocationId(task.getServiceOrder().getServiceLocation().getId());
        cable.setPortId(portDAO.findById(circuit.getPortId()).getId());
        cableDAO.add(cable);
        task.setStatus(Task.Status.COMPLETED);
        taskDAO.update(task);
        request.setAttribute("task", task);
        Workflow.createTaskForPE(task.getServiceOrder(), Task.Type.CREATE_CIRCUIT, taskDAO);
        return "/WEB-INF/ie/ie-page-selected-task.jsp";
    }

}
