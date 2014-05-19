package com.netcracker.wind.paging;

import com.netcracker.wind.dao.factory.FactoryCreator;
import com.netcracker.wind.dao.interfaces.ITaskDAO;
import java.util.List;

/**
 * The {@code TasksPaginatedList} class designed to creating paginated list of
 * task for certain user groups.
 * 
 * @author Alexander Kovriga
 */
public class TasksPaginatedList extends AbstractPaginatedList {
    
    private final ITaskDAO taskDAO
            = FactoryCreator.getInstance().getFactory().createTaskDAO();
    private final int roleId;

    public TasksPaginatedList(int roleId, int pageSize) {
        super(pageSize);
        this.roleId = roleId;
    }

    @Override
    public List getList() {
        return taskDAO.findByGroup(roleId, pageNumber, pageSize);
    }

    @Override
    public int getFullListSize() {
        return taskDAO.countRows();
    }
    
}
