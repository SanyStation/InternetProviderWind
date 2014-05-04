package com.netcracker.wind.paging;

import com.netcracker.wind.dao.factory.FactoryCreator;
import com.netcracker.wind.dao.interfaces.ITaskDAO;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Alexander Kovriga
 */
public class TasksPaginatedList extends AbstractPaginatedList {
    
    private final ITaskDAO taskDAO
            = FactoryCreator.getInstance().getFactory().createTaskDAO();
    private final int roleId;

    public TasksPaginatedList(HttpServletRequest request, int roleId, 
            int pageSize) {
        super(request, pageSize);
        this.roleId = roleId;
    }

    @Override
    public List getList() {
        return taskDAO.findByGroup(roleId, pageNumber, pageSize);
    }

    @Override
    public int getFullListSize() {
        return taskDAO.getRows();
    }
    
}
