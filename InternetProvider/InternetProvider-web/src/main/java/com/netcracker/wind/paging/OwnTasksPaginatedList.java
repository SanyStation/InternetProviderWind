package com.netcracker.wind.paging;

import com.netcracker.wind.dao.factory.FactoryCreator;
import com.netcracker.wind.dao.interfaces.ITaskDAO;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Oksana
 */
public class OwnTasksPaginatedList extends AbstractPaginatedList {

    private int performerID;
    private final ITaskDAO taskDAO = FactoryCreator.getInstance().getFactory().
            createTaskDAO();

    public OwnTasksPaginatedList(HttpServletRequest request, int pageSize) {
        super(request, pageSize);
    }

    @Override
    public List getList() {
        System.out.println("useruser");
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
