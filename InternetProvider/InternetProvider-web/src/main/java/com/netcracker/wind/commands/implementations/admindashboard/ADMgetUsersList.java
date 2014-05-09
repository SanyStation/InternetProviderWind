package com.netcracker.wind.commands.implementations.admindashboard;

import com.netcracker.wind.commands.ICommand;
import com.netcracker.wind.dao.implementations.helper.AbstractOracleDAO;
import com.netcracker.wind.paging.ADMUsersPaginatedList;
import com.netcracker.wind.paging.IExtendedPaginatedList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Alexander Kovriga
 */
public class ADMgetUsersList implements ICommand {

    public String execute(HttpServletRequest request,
            HttpServletResponse response) {
        IExtendedPaginatedList paginatedList
                = new ADMUsersPaginatedList(request,
                        AbstractOracleDAO.DEFAULT_PAGE_SIZE);
        HttpSession session = request.getSession(false);
        if (session == null) {
            return "";
        }
        session.setAttribute("users", paginatedList);
        return "/WEB-INF/admin/adm-page-users-list.jsp";
    }
}
