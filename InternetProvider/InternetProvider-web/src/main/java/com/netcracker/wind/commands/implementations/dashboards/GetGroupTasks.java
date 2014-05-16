package com.netcracker.wind.commands.implementations.dashboards;

import com.netcracker.wind.dao.implementations.helper.AbstractOracleDAO;
import com.netcracker.wind.paging.IExtendedPaginatedList;
import com.netcracker.wind.paging.TaskPaginationList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Oksana
 */
public class GetGroupTasks{

    private static final String TASKS = "tasks";
    private final int groupId;
    private final String pageForReturn;

    public GetGroupTasks(int groupId, String pageForReturn) {
        this.groupId = groupId;
        this.pageForReturn = pageForReturn;
    }

    public String execute(HttpServletRequest request,
            HttpServletResponse response) {
        IExtendedPaginatedList paginatedList = new TaskPaginationList(
                AbstractOracleDAO.DEFAULT_PAGE_SIZE).setGroup(groupId);
        paginatedList.setRequest(request);
        HttpSession session = request.getSession(false);
        session.setAttribute(TASKS, paginatedList);
        return pageForReturn;
    }
}
