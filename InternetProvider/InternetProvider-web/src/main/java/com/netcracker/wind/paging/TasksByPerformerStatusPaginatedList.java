package com.netcracker.wind.paging;

import com.netcracker.wind.dao.factory.FactoryCreator;
import com.netcracker.wind.dao.interfaces.ITaskDAO;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Oksana
 */
public class TasksByPerformerStatusPaginatedList extends AbstractPaginatedList {

    private int performerId;
    private String status;
    private final ITaskDAO taskDAO = FactoryCreator.getInstance().getFactory().
            createTaskDAO();

    public TasksByPerformerStatusPaginatedList(HttpServletRequest request,
            int pageSize) {
        super(request, pageSize);
    }

    @Override
    public List getList() {
        return taskDAO.findByPerformerStatus(performerId, status, pageNumber, 
                pageSize);
    }

    @Override
    public int getFullListSize() {
        return taskDAO.countRows();
    }
    
    public TasksByPerformerStatusPaginatedList setPerformer(int performerId) {
        this.performerId = performerId;
        return this;
    }

    public TasksByPerformerStatusPaginatedList setStatus(String status) {
        this.status = status;
        return this;
    }
}
