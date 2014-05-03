package com.netcracker.wind.paging;

import javax.servlet.http.HttpServletRequest;
import org.displaytag.properties.SortOrderEnum;

/**
 *
 * @author Alexander Kovriga
 */
public abstract class AbstractPaginatedList implements IExtendedPaginatedList {
    
    protected int pageNumber;
    protected int pageSize;

    public AbstractPaginatedList(HttpServletRequest request,
            int pageSize) {
        String page = (String) request.getParameter(ATTRIBUTE_PAGE);
        if (page == null) {
            page = "1";
        }
        this.pageNumber = Integer.parseInt(page);
        this.pageSize = pageSize;
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
    public String getSortCriterion() {return "";}

    @Override
    public SortOrderEnum getSortDirection() {return SortOrderEnum.ASCENDING;}

    @Override
    public String getSearchId() {return null;}

}
