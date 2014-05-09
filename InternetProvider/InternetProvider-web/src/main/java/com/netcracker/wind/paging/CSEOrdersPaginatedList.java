package com.netcracker.wind.paging;

import com.netcracker.wind.dao.factory.FactoryCreator;
import com.netcracker.wind.dao.interfaces.IServiceOrderDAO;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Oksana
 */
public class CSEOrdersPaginatedList extends AbstractPaginatedList {

    private final IServiceOrderDAO orderDAO = FactoryCreator.getInstance().
            getFactory().createServiceOrderDAO();
    private int userId;

    public CSEOrdersPaginatedList(HttpServletRequest request, int pageSize) {
        super(request, pageSize);
    }

    @Override
    public List getList() {
        return orderDAO.findByUser(userId, pageNumber, pageSize);
    }

    @Override
    public int getFullListSize() {
        return orderDAO.countRows();
    }

    public CSEOrdersPaginatedList setUser(int userId) {
        this.userId = userId;
        return this;
    }

}
