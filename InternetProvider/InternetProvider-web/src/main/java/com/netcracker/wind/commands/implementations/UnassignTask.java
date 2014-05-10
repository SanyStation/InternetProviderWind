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
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
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
            //TODO return to error page
            return "";
        }
        User user = (User) request.getSession(false).getAttribute(USER);
        if (task.getUserId() != user.getId()) {
            return "/WEB-INF/generic/wrong-selected-task.jsp";
        }
        task.setStatus(Task.Status.NEW);
        task.setUserId(0);
        taskDAO.update(task);
        
        List<User> users = FactoryCreator.getInstance().getFactory().
                createUserDAO().findByRole(task.getRoleId());
        if (users != null) {
            new MailSendler().sendEmail(users, task.getType().toString(),
                    new FormatedMail().getInformGroupAboutTaskMessage(task));
        }
        
        return "/profile";
    }
}
