package com.netcracker.wind.paging;

import javax.servlet.http.HttpServletRequest;
import org.displaytag.pagination.PaginatedList;

/**
 *
 * @author Alexander Kovriga
 */
public interface IExtendedPaginatedList extends PaginatedList {

    public static final String ATTRIBUTE_PAGE = "page";
    public static final int DEFAULT_PAGE_NUMBER = 1;
    public static final int DEFAULT_PAGE_SIZE = 30;

    public void setRequest(HttpServletRequest request);

    public void setPageSize(int pageSize);

}
