package com.netcracker.wind.commands.implementations.iedashboard;

import com.netcracker.wind.commands.ICommand;
import com.netcracker.wind.dao.factory.AbstractFactoryDAO;
import com.netcracker.wind.dao.factory.FactoryCreator;
import com.netcracker.wind.dao.interfaces.ICableDAO;
import com.netcracker.wind.dao.interfaces.IPortDAO;
import com.netcracker.wind.dao.interfaces.ITaskDAO;
import com.netcracker.wind.entities.Cable;
import com.netcracker.wind.entities.Port;
import com.netcracker.wind.entities.Task;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author �����
 */
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
        if (!task.getStatus().equals(Task.Status.ACTIVE)) {
            return "/WEB-INF/ie/ie-page-selected-task.jsp";
        }
        Cable cable = task.getServiceOrder().getServiceLocation().getCable();

        Port port = cable.getPort();
        port.setFree(true);
        portDAO.update(port);
        cableDAO.delete(cable.getId());
        task.setStatus(Task.Status.COMPLETED);
        taskDAO.update(task);
        request.getSession(false).setAttribute(TASK, task);

        return "/WEB-INF/ie/ie-page-selected-task.jsp";
    }

}
