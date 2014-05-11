package com.netcracker.wind.paging;

import com.netcracker.wind.dao.factory.FactoryCreator;
import com.netcracker.wind.dao.interfaces.ITaskDAO;
import com.netcracker.wind.entities.Task;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Oksana
 */
public class TaskPaginationList extends AbstractPaginatedList {

    private int groupId;
    private final ITaskDAO taskDAO = FactoryCreator.getInstance().getFactory().
            createTaskDAO();

    public TaskPaginationList(HttpServletRequest request, int pageSize) {
        super(request, pageSize);
    }

    @Override
    public List getList() {
        return taskDAO.findByGroupStatus(groupId, Task.Status.NEW.toString(),
                pageNumber, pageSize, sortCriterion, direction);
    }

    @Override
    public int getFullListSize() {
        return taskDAO.countRows();
    }

    public TaskPaginationList setGroup(int groupId) {
        this.groupId = groupId;
        return this;
    }

}
