package com.netcracker.wind.paging;

import com.netcracker.wind.dao.factory.FactoryCreator;
import com.netcracker.wind.dao.interfaces.IUserDAO;
import com.netcracker.wind.entities.Role;
import java.util.List;

/**
 * Class allow to get list of customer users.
 *
 * @author Anatolii
 */
public class CSEUsersPaginatedList extends AbstractPaginatedList {

    private final IUserDAO userDAO = FactoryCreator.getInstance().getFactory().
            createUserDAO();

    /**
     * Constructor for creating instance of {@link CSEUsersPaginatedList}
     *
     * @param pageSize size of one page that will be get under one request to
     * database.
     */
    public CSEUsersPaginatedList(int pageSize) {
        super(pageSize);
    }

    /**
     * Method for finding list of users.
     *
     * @return list of customer users. Size of list must be set when creating
     * instance of class.
     */
    @Override
    public List getList() {
        return userDAO.findByRole(Role.CU_GROUP_ID, pageNumber, pageSize,
                sortCriterion, direction);
    }

    /**
     * Method allows get count of customer users that was be finding.
     *
     * @return count of users.
     */
    @Override
    public int getFullListSize() {
        return userDAO.countRows();
    }

}
