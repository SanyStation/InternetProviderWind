package com.netcracker.wind.paging;

import com.netcracker.wind.dao.factory.FactoryCreator;
import com.netcracker.wind.dao.interfaces.ITaskDAO;
import java.util.List;

/**
 *
 * @author Oksana
 */
public class OwnTasksPaginatedList extends AbstractPaginatedList {

    private int performerID;
    private final ITaskDAO taskDAO = FactoryCreator.getInstance().getFactory().
            createTaskDAO();

    public OwnTasksPaginatedList(int pageSize) {
        super(pageSize);
    }

    @Override
    public List getList() {
        return taskDAO.findByPerformer(performerID, pageNumber, pageSize);
    }

    @Override
    public int getFullListSize() {
        return taskDAO.countRows();
    }
    
    public OwnTasksPaginatedList setPerformer(int performerId) {
        this.performerID = performerId;
        return this;
    }

}
