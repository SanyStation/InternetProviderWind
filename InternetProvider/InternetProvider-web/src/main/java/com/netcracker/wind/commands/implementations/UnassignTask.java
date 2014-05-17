package com.netcracker.wind.commands.implementations;

import com.netcracker.wind.annotations.RolesAllowed;
import com.netcracker.wind.commands.ICommand;
import com.netcracker.wind.dao.factory.FactoryCreator;
import com.netcracker.wind.dao.interfaces.ITaskDAO;
import com.netcracker.wind.entities.Role;
import com.netcracker.wind.entities.Task;
import com.netcracker.wind.entities.User;
import com.netcracker.wind.mail.FormatedMail;
import com.netcracker.wind.mail.MailSendler;
import com.netcracker.wind.manager.ConfigurationManager;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This class-command allows unassign particular task for engineer if task is
 * active. After task be with status NEW and email notification will be sent
 * about new task to all engineers under particular group except engineer that
 * unassign task.
 *
 * @author Anatolii
 */
@RolesAllowed(roles
        = {Role.Roles.CustomerSupportEngineer,
            Role.Roles.InstallationEngineer,
            Role.Roles.ProvisioningEngineer})
public class UnassignTask implements ICommand {

    private static final String TASK_ID = "task_id";
    private static final String USER = "user";

    public String execute(HttpServletRequest request, HttpServletResponse response) {
        int taskId = Integer.parseInt(request.getParameter(TASK_ID));
        ITaskDAO taskDAO = FactoryCreator.getInstance().getFactory().createTaskDAO();
        Task task = taskDAO.findById(taskId);
        if (task == null) {
            return manager.getProperty(ConfigurationManager.PAGE_WRON_SELECTED_TASK);
        }
        User user = (User) request.getSession(false).getAttribute(USER);
        if (task.getUserId() != user.getId()
                || !task.getStatus().equals(Task.Status.ACTIVE)) {
            return manager.getProperty(ConfigurationManager.PAGE_WRON_SELECTED_TASK);
        }
        task.setStatus(Task.Status.NEW);
        task.setUserId(0);
        taskDAO.update(task);

        List<User> users = FactoryCreator.getInstance().getFactory().
                createUserDAO().findByRole(task.getRoleId());
        if (users != null) {
            users.remove(user);
            new MailSendler().sendEmail(users, task.getType().toString(),
                    new FormatedMail().getInformGroupAboutTaskMessage(task));
        }

        return manager.getProperty(ConfigurationManager.PAGE_PROFILE);
    }
}
