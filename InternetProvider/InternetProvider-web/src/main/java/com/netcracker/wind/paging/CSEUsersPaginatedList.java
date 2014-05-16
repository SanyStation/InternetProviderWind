package com.netcracker.wind.paging;

import com.netcracker.wind.dao.factory.FactoryCreator;
import com.netcracker.wind.dao.interfaces.IUserDAO;
import com.netcracker.wind.entities.Role;
import java.util.List;

/**
 *
 * @author Anatolii
 */
public class CSEUsersPaginatedList extends AbstractPaginatedList {

    private final IUserDAO userDAO = FactoryCreator.getInstance().getFactory().
            createUserDAO();

    public CSEUsersPaginatedList(int pageSize) {
        super(pageSize);
    }

    @Override
    public List getList() {
        return userDAO.findByRole(Role.CU_GROUP_ID, pageNumber, pageSize,
                sortCriterion, direction);
    }

    @Override
    public int getFullListSize() {
        return userDAO.countRows();
    }

}
