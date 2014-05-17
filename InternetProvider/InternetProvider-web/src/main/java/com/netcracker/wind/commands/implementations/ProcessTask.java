package com.netcracker.wind.commands.implementations;

import com.netcracker.wind.annotations.RolesAllowed;
import com.netcracker.wind.commands.ICommand;
import com.netcracker.wind.dao.factory.FactoryCreator;
import com.netcracker.wind.dao.interfaces.ITaskDAO;
import com.netcracker.wind.entities.Role;
import com.netcracker.wind.entities.Task;
import com.netcracker.wind.entities.User;
import com.netcracker.wind.manager.ConfigurationManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This class-command was designed for processing event when selecting task.
 *
 * @author Anatolii
 */
@RolesAllowed(roles
        = {Role.Roles.CustomerSupportEngineer,
            Role.Roles.InstallationEngineer,
            Role.Roles.ProvisioningEngineer})
public class ProcessTask implements ICommand {

    private static final String TASK_ID = "task_id";
    private static final String USER = "user";
    private static final String TASK = "task";

    public String execute(HttpServletRequest request, HttpServletResponse response) {
        int taskId = Integer.parseInt(request.getParameter(TASK_ID));
        ITaskDAO taskDAO = FactoryCreator.getInstance().getFactory().createTaskDAO();
        Task task = taskDAO.findById(taskId);

        User user = (User) request.getSession(false).getAttribute(USER);
        if (task == null || user.getRoleId() != task.getRoleId()) {
            return manager.getProperty(ConfigurationManager.PAGE_WRON_SELECTED_TASK);
        }
        String page = "";

        if (task.getStatus().equals(Task.Status.NEW)) {
            task = taskDAO.occupyTaskByID(taskId, user.getId());
        }
        if (task == null) {
            page = manager.getProperty(ConfigurationManager.PAGE_WRON_SELECTED_TASK);
        } else if (user.getRoleId() == Role.CSE_GROUP_ID) {
            page = manager.getProperty(ConfigurationManager.PAGE_CSE_SELECTED_TASK);
        } else if (user.getRoleId() == Role.PE_GROUP_ID) {
            page = manager.getProperty(ConfigurationManager.PAGE_PE_SELECTED_TASK);
        } else if (user.getRoleId() == Role.IE_GROUP_ID) {
            page = manager.getProperty(ConfigurationManager.PAGE_IE_SELECTED_TASK);
        }
        if (task != null && user.getId() != task.getUserId()) {
            page = manager.getProperty(ConfigurationManager.PAGE_WRON_SELECTED_TASK);
        } else {
            request.setAttribute(TASK, task);
        }
        return page;
    }

}
