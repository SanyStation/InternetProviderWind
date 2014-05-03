package com.netcracker.wind.commands.implementations.pedashboard;

import com.netcracker.wind.commands.ICommand;
import com.netcracker.wind.dao.factory.AbstractFactoryDAO;
import com.netcracker.wind.dao.factory.FactoryCreator;
import com.netcracker.wind.dao.interfaces.ITaskDAO;
import com.netcracker.wind.entities.Task;
import com.netcracker.wind.workflow.Workflow;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Anatolii
 */
public class SetupCircuit implements ICommand {

    private static final String TASK_ID = "task_id";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        int taskID = -1;
        try {
            taskID = Integer.parseInt(request.getParameter(TASK_ID));
        } catch (NumberFormatException exception) {
            //TODO log
            //TODO redirecct to error page
            return "";
        }
        if (taskID == -1) {
            //TODO return to error page
        }

        AbstractFactoryDAO factoryDAO = FactoryCreator.getInstance().getFactory();
        ITaskDAO taskDAO = factoryDAO.createTaskDAO();

        Task task = taskDAO.findById(taskID);

        //TODO setting circuit
        //Circuit circuit = task.getServiceOrder().getServiceInstance().getCircuit();
        task.setStatus(Task.Status.COMPLETED);
        taskDAO.update(task);
        Workflow.createTaskForCSE(task.getServiceOrder(), taskDAO);
        //TODO return next page
        return "";
    }

}
