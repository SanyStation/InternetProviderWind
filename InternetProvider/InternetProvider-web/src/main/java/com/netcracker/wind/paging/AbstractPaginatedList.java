package com.netcracker.wind.paging;

import com.netcracker.wind.dao.implementations.helper.AbstractOracleDAO;
import com.netcracker.wind.dao.implementations.helper.AbstractOracleDAO.Direction;
import javax.servlet.http.HttpServletRequest;
import org.displaytag.properties.SortOrderEnum;

/**
 *
 * @author Alexander Kovriga
 */
public abstract class AbstractPaginatedList implements IExtendedPaginatedList {
    
    protected int pageNumber;
    protected int pageSize;
    protected String sortCriterion;
    protected Direction direction;

    public AbstractPaginatedList(HttpServletRequest request, int pageSize) {
        String page = (String) request.getParameter(ATTRIBUTE_PAGE);
        String dir = request.getParameter(ATTRIBUTE_SORT_DIRECTION);
        String criterion = request.getParameter(ATTRIBUTE_SORT_CRITERION);
        if (page == null) {
            this.pageNumber = AbstractOracleDAO.DEFAULT_PAGE_NUMBER;
        } else {
            this.pageNumber = Integer.parseInt(page);
        }
        if (dir == null) {
            direction = Direction.ASC;
        } else {
            if (dir.equals("asc")) {
                direction = Direction.ASC;
            } else {
                direction = Direction.DESC;
            }
        }
        if (criterion == null) {
            sortCriterion = "";
        } else {
            sortCriterion = criterion;
        }
        this.pageSize = pageSize;
    }
    
    @Override
    public void setRequest(HttpServletRequest request) {
        String page = (String) request.getParameter(ATTRIBUTE_PAGE);
        String dir = request.getParameter(ATTRIBUTE_SORT_DIRECTION);
        String criterion = request.getParameter(ATTRIBUTE_SORT_CRITERION);
        if (page == null) {
            this.pageNumber = AbstractOracleDAO.DEFAULT_PAGE_NUMBER;
        } else {
            this.pageNumber = Integer.parseInt(page);
        }
        if (dir == null) {
            direction = Direction.ASC;
        } else {
            if (dir.equals("asc")) {
                direction = Direction.ASC;
            } else {
                direction = Direction.DESC;
            }
        }
        if (criterion == null) {
            sortCriterion = "";
        } else {
            sortCriterion = criterion;
        }
    }

    @Override
    public int getPageNumber() {
        return pageNumber;
    }
    
    @Override
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    @Override
    public int getObjectsPerPage() {
        return pageSize;
    }

    @Override
    public String getSortCriterion() {
        return sortCriterion;
    }

    @Override
    public SortOrderEnum getSortDirection() {        
        if ("ASC".equals(direction.toString())) {
            return SortOrderEnum.ASCENDING;
        } else {
            return SortOrderEnum.DESCENDING;
        }
    }

    @Override
    public String getSearchId() {
        return null;
    }

}
