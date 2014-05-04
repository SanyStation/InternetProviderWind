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

    public CSEOrdersPaginatedList(HttpServletRequest request, int pageSize) {
        super(request, pageSize);
    }

    @Override
    public List getList() {
        return orderDAO.findAll(pageNumber, pageSize);
    }

    @Override
    public int getFullListSize() {
        return orderDAO.countRows();
    }

}
