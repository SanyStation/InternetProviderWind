package com.netcracker.wind.paging;

import com.netcracker.wind.dao.factory.FactoryCreator;
import com.netcracker.wind.dao.interfaces.IUserDAO;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Anatolii
 */
public class ADMUsersPaginatedList extends AbstractPaginatedList {

    private final IUserDAO userDAO = FactoryCreator.getInstance().getFactory().
            createUserDAO();

    public ADMUsersPaginatedList(HttpServletRequest request, int pageSize) {
        super(request, pageSize);
    }

    @Override
    public List getList() {
        return userDAO.findAll(pageNumber, pageSize);
    }

    @Override
    public int getFullListSize() {
        return userDAO.countRows();
    }

}
