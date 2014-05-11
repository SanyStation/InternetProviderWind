package com.netcracker.wind.paging;

import com.netcracker.wind.dao.factory.FactoryCreator;
import com.netcracker.wind.dao.interfaces.IServiceOrderDAO;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Oksana
 */
public class OrderUserPaginatedList extends AbstractPaginatedList {

    private int performerId;

    private final IServiceOrderDAO serviceOrderDAO = FactoryCreator.getInstance().getFactory()
            .createServiceOrderDAO();

    public OrderUserPaginatedList(HttpServletRequest request, int pageSize) {
        super(request, pageSize);
    }

    @Override
    public List getList() {
        return serviceOrderDAO.findByUser(performerId, pageNumber, pageSize);
    }

    @Override
    public int getFullListSize() {
        return serviceOrderDAO.countRows();
    }

    public OrderUserPaginatedList setPerformer(int performerId) {
        this.performerId = performerId;
        return this;
    }
}
