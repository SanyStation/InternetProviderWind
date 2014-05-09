package com.netcracker.wind.commands.implementations.pedashboard;

import com.netcracker.wind.commands.ICommand;
import com.netcracker.wind.dao.factory.AbstractFactoryDAO;
import com.netcracker.wind.dao.factory.FactoryCreator;
import com.netcracker.wind.dao.interfaces.ICircuitDAO;
import com.netcracker.wind.dao.interfaces.IPortDAO;
import com.netcracker.wind.dao.interfaces.ITaskDAO;
import com.netcracker.wind.entities.Circuit;
import com.netcracker.wind.entities.Port;
import com.netcracker.wind.entities.Task;
import com.netcracker.wind.workflow.Workflow;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Anatolii
 */
public class DeleteCircuit implements ICommand {

    private static final String TASK_ID = "task_id";
    private static final String TASK = "task";

    public String execute(HttpServletRequest request, HttpServletResponse response) {
        int taskId = Integer.parseInt(request.getParameter(TASK_ID));

        AbstractFactoryDAO factoryDAO = FactoryCreator.getInstance().getFactory();
        ITaskDAO taskDAO = factoryDAO.createTaskDAO();
        ICircuitDAO circuitDAO = factoryDAO.createCircuitDAO();
        IPortDAO portDAO = factoryDAO.createPortDAO();

        Task task = taskDAO.findById(taskId);

        Circuit circuit = task.getServiceOrder().getServiceInstance().getCircuit();
        Port port = circuit.getPort();
        port.setFree(true);
        portDAO.update(port);
        circuitDAO.delete(circuit.getId());
        task.setStatus(Task.Status.COMPLETED);
        taskDAO.update(task);
        request.setAttribute(TASK, task);
        Workflow.createTaskForIE(task.getServiceOrder(),
                Task.Type.DELETE_CABLE, taskDAO);
        
        return "/WEB-INF/pe/pe-page-selected-task.jsp";
    }

}
