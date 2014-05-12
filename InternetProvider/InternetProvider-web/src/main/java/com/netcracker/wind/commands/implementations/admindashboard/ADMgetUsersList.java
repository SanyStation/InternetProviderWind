package com.netcracker.wind.commands.implementations.admindashboard;

import com.netcracker.wind.annotations.RolesAllowed;
import com.netcracker.wind.commands.ICommand;
import com.netcracker.wind.dao.implementations.helper.AbstractOracleDAO;
import com.netcracker.wind.entities.Role;
import com.netcracker.wind.manager.ConfigurationManager;
import com.netcracker.wind.paging.ADMUsersPaginatedList;
import com.netcracker.wind.paging.IExtendedPaginatedList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Alexander Kovriga
 */
@RolesAllowed(roles = Role.Roles.Administrator)
public class ADMgetUsersList implements ICommand {

    public String execute(HttpServletRequest request,
            HttpServletResponse response) {
        IExtendedPaginatedList paginatedList
                = new ADMUsersPaginatedList(request,
                        AbstractOracleDAO.DEFAULT_PAGE_SIZE);
        HttpSession session = request.getSession(false);
        session.setAttribute("users", paginatedList);
        return manager.getProperty(ConfigurationManager.PAGE_ADM_USERS_LIST);
    }
}
