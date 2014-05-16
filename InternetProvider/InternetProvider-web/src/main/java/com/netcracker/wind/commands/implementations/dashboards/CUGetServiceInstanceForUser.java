package com.netcracker.wind.commands.implementations.dashboards;

import com.netcracker.wind.annotations.RolesAllowed;
import com.netcracker.wind.commands.ICommand;
import com.netcracker.wind.dao.implementations.helper.AbstractOracleDAO;
import com.netcracker.wind.entities.Role;
import com.netcracker.wind.entities.User;
import com.netcracker.wind.manager.ConfigurationManager;
import com.netcracker.wind.paging.IExtendedPaginatedList;
import com.netcracker.wind.paging.SIUserPaginationList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author j_mart
 */
@RolesAllowed(roles = Role.Roles.CustomerUser)
public class CUGetServiceInstanceForUser implements ICommand {

    private final String SI = "service_instances";
    private static final String USER = "user";

    public String execute(HttpServletRequest request,
            HttpServletResponse response) {

        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute(USER);
        int performerId = user.getId();
        IExtendedPaginatedList paginatedList = new SIUserPaginationList(
                AbstractOracleDAO.DEFAULT_PAGE_SIZE).setPerformer(performerId);
        paginatedList.setRequest(request);
        session.setAttribute(SI, paginatedList);
        return manager.getProperty(
                ConfigurationManager.PAGE_CU_PAGE_INSTANCES_LIST);

    }

}
