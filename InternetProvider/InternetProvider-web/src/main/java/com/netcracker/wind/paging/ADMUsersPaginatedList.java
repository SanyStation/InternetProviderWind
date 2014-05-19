package com.netcracker.wind.paging;

import com.netcracker.wind.dao.factory.FactoryCreator;
import com.netcracker.wind.dao.interfaces.IUserDAO;
import java.util.List;

/**
 * Class allow to get list of all users.
 *
 * @author Anatolii
 */
public class ADMUsersPaginatedList extends AbstractPaginatedList {

    private final IUserDAO userDAO = FactoryCreator.getInstance().getFactory().
            createUserDAO();

    /**
     * Constructor for creating instance of {@link ADMUsersPaginatedList}
     *
     * @param pageSize size of one page that will be get under one request to
     * database.
     */
    public ADMUsersPaginatedList(int pageSize) {
        super(pageSize);
    }

    /**
     * Method for finding list of users.
     *
     * @return list of users. Size of list must be set when creating instance of
     * class.
     */
    @Override
    public List getList() {
        return userDAO.findAll(pageNumber, pageSize, sortCriterion, direction);
    }

    /**
     * Method allows get count of users that was be finding.
     *
     * @return count of users.
     */
    @Override
    public int getFullListSize() {
        return userDAO.countRows();
    }

}
