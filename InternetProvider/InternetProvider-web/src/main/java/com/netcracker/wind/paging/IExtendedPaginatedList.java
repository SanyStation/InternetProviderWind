package com.netcracker.wind.paging;

import javax.servlet.http.HttpServletRequest;
import org.displaytag.pagination.PaginatedList;

/**
 * The {@code IExtendedPaginatedList} provides the extended interface of
 * {@code PaginatedList} for paginated list of Displaytag library.
 * 
 * @author Alexander Kovriga
 */
public interface IExtendedPaginatedList extends PaginatedList {

    public static final String ATTRIBUTE_PAGE = "page";
    public static final String ATTRIBUTE_SORT_DIRECTION = "dir";
    public static final String ATTRIBUTE_SORT_CRITERION = "sort";

    /**
     * Sets the request for further get from him parameters like number of page,
     * sort criterion and sort direction.
     * 
     * @param request simple http servlet request
     */
    public void setRequest(HttpServletRequest request);

    /**
     * Sets the size of page of paginated list.
     * 
     * @param pageSize count of record per page
     */
    public void setPageSize(int pageSize);

}
