package com.netcracker.wind.paging;

import com.netcracker.wind.dao.factory.FactoryCreator;
import com.netcracker.wind.dao.interfaces.IUserDAO;
import java.util.List;

/**
 *
 * @author Anatolii
 */
public class ADMUsersPaginatedList extends AbstractPaginatedList {

    private final IUserDAO userDAO = FactoryCreator.getInstance().getFactory().
            createUserDAO();

    public ADMUsersPaginatedList(int pageSize) {
        super(pageSize);
    }

    @Override
    public List getList() {
        return userDAO.findAll(pageNumber, pageSize, sortCriterion, direction);
    }

    @Override
    public int getFullListSize() {
        return userDAO.countRows();
    }

}
