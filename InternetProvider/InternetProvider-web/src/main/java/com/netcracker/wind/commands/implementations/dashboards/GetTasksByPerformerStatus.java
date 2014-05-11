package com.netcracker.wind.commands.implementations.dashboards;

import com.netcracker.wind.dao.implementations.helper.AbstractOracleDAO;
import com.netcracker.wind.entities.Task;
import com.netcracker.wind.entities.User;
import com.netcracker.wind.paging.IExtendedPaginatedList;
import com.netcracker.wind.paging.TasksByPerformerStatusPaginatedList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Oksana
 */
public class GetTasksByPerformerStatus {

    private static final String TASKS = "tasks";
    private final String pageForReturn;
    private final Task.Status status;

    public GetTasksByPerformerStatus(Task.Status status, String pageForReturn) {
        this.pageForReturn = pageForReturn;
        this.status = status;
    }

    public String execute(HttpServletRequest request,
            HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("user");
        int performerId = user.getId();
        IExtendedPaginatedList paginatedList
                = new TasksByPerformerStatusPaginatedList(request,
                AbstractOracleDAO.DEFAULT_PAGE_SIZE).
                        setPerformer(performerId).addStatus(status);
        session.setAttribute(TASKS, paginatedList);
        return pageForReturn;
    }
}
