package com.netcracker.wind.commands.implementations.csedashboard;

import com.netcracker.wind.annotations.RolesAllowed;
import com.netcracker.wind.commands.ICommand;
import com.netcracker.wind.dao.implementations.helper.AbstractOracleDAO;
import com.netcracker.wind.entities.Role;
import com.netcracker.wind.manager.ConfigurationManager;
import com.netcracker.wind.paging.IExtendedPaginatedList;
import com.netcracker.wind.paging.TaskPaginationList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * don't need any more
 *
 * @author Oksana
 */
@RolesAllowed(roles = Role.Roles.CustomerSupportEngineer)
public class CSEgetGroupTasks implements ICommand {

    private static final String TASKS = "tasks";

    public String execute(HttpServletRequest request, HttpServletResponse response) {
        IExtendedPaginatedList paginatedList = new TaskPaginationList(request,
                AbstractOracleDAO.DEFAULT_PAGE_SIZE).setGroup(Role.CSE_GROUP_ID);
        HttpSession session = request.getSession(false);
        session.setAttribute(TASKS, paginatedList);
        return manager.getProperty(ConfigurationManager.PAGE_CSE_TASKS_LIST);

    }

}
