package com.netcracker.wind.commands.implementations.pedashboard;

import com.netcracker.wind.commands.ICommand;
import com.netcracker.wind.entities.Role;
import com.netcracker.wind.manager.ConfigurationManager;
import com.netcracker.wind.paging.IExtendedPaginatedList;
import com.netcracker.wind.paging.TasksPaginatedList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Alexander Kovriga
 */
public class Test implements ICommand {

    public String execute(HttpServletRequest request,
            HttpServletResponse response) {
        IExtendedPaginatedList expl = new TasksPaginatedList(request, 
                Role.PE_GROUP_ID, IExtendedPaginatedList.DEFAULT_PAGE_SIZE);
        request.getSession().setAttribute("tasks", expl);
        return ConfigurationManager.getInstance().
                getProperty(ConfigurationManager.PAGE_PE_DASHBOARD);
    }
    
}
