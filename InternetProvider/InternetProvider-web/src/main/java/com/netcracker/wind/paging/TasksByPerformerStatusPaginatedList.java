package com.netcracker.wind.paging;

import com.netcracker.wind.dao.factory.FactoryCreator;
import com.netcracker.wind.dao.interfaces.ITaskDAO;
import com.netcracker.wind.entities.Task;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Oksana
 */
public class TasksByPerformerStatusPaginatedList extends AbstractPaginatedList {

    private int performerId;
    private final List<Task.Status> statuses;
    private final ITaskDAO taskDAO = FactoryCreator.getInstance().getFactory().
            createTaskDAO();

    public TasksByPerformerStatusPaginatedList(HttpServletRequest request,
            int pageSize) {
        super(request, pageSize);
        statuses = new ArrayList<Task.Status>(4);
    }

    @Override
    public List getList() {
        List result = new ArrayList();
        for (Task.Status status : statuses) {
            result.addAll(taskDAO.findByPerformerStatus(performerId,
                    status.toString(), pageNumber, pageSize, sortCriterion,
                    direction));
        }
        return result;
    }

    @Override
    public int getFullListSize() {
        return taskDAO.countRows();
    }

    public TasksByPerformerStatusPaginatedList setPerformer(int performerId) {
        this.performerId = performerId;
        return this;
    }

    public TasksByPerformerStatusPaginatedList addStatus(Task.Status status) {
        this.statuses.add(status);
        return this;
    }
}
