package com.netcracker.wind.commands.implementations.pedashboard;

import com.netcracker.wind.annotations.RolesAllowed;
import com.netcracker.wind.commands.ICommand;
import com.netcracker.wind.dao.factory.AbstractFactoryDAO;
import com.netcracker.wind.dao.factory.FactoryCreator;
import com.netcracker.wind.dao.interfaces.ITaskDAO;
import com.netcracker.wind.entities.Role;
import com.netcracker.wind.entities.Task;
import com.netcracker.wind.workflow.Workflow;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Anatolii
 */
@RolesAllowed(roles = Role.Roles.ProvisioningEngineer)
public class SetupCircuit implements ICommand {

    private static final String TASK_ID = "task_id";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        int taskID = Integer.parseInt(request.getParameter(TASK_ID));

        AbstractFactoryDAO factoryDAO = FactoryCreator.getInstance().getFactory();
        ITaskDAO taskDAO = factoryDAO.createTaskDAO();

        Task task = taskDAO.findById(taskID);

        //TODO setting circuit
        //Circuit circuit = task.getServiceOrder().getServiceInstance().getCircuit();
        task.setStatus(Task.Status.COMPLETED);
        taskDAO.update(task);
        request.setAttribute("task", task);
        Workflow.createTaskForCSE(task.getServiceOrder(), taskDAO);
        //TODO return next page
        return "/WEB-INF/pe/pe-page-selected-task.jsp";
    }

}
