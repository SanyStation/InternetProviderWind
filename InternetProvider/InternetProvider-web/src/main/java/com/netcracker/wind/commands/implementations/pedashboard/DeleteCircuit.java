package com.netcracker.wind.commands.implementations.pedashboard;

import com.netcracker.wind.commands.ICommand;
import com.netcracker.wind.dao.factory.AbstractFactoryDAO;
import com.netcracker.wind.dao.factory.FactoryCreator;
import com.netcracker.wind.dao.interfaces.ICircuitDAO;
import com.netcracker.wind.dao.interfaces.IPortDAO;
import com.netcracker.wind.dao.interfaces.IServiceInstanceDAO;
import com.netcracker.wind.dao.interfaces.ITaskDAO;
import com.netcracker.wind.entities.Circuit;
import com.netcracker.wind.entities.ServiceInstance;
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
        IServiceInstanceDAO serviceInstanceDAO = factoryDAO.createServiceInstanceDAO();

        Task task = taskDAO.findById(taskId);
        if (!task.getStatus().equals(Task.Status.ACTIVE)) {
            return "/WEB-INF/pe/pe-page-selected-task.jsp";
        }

        ServiceInstance serviceInstance = task.getServiceOrder().getServiceInstance();
        serviceInstance.setStatus(ServiceInstance.Status.DISCONNECT);
        Circuit circuit = serviceInstance.getCircuit();
        circuitDAO.delete(circuit.getId());
        serviceInstanceDAO.update(serviceInstance);
        task.setStatus(Task.Status.COMPLETED);
        taskDAO.update(task);
        request.setAttribute(TASK, task);
        Workflow.createTaskForIE(task.getServiceOrder(),
                Task.Type.DELETE_CABLE, taskDAO);

        return "/WEB-INF/pe/pe-page-selected-task.jsp";
    }

}
