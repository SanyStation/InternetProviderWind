package com.netcracker.wind.paging;

import com.netcracker.wind.dao.factory.AbstractFactoryDAO;
import com.netcracker.wind.dao.factory.FactoryCreator;
import java.util.List;
import org.displaytag.pagination.PaginatedList;
import org.displaytag.properties.SortOrderEnum;

/**
 *
 * @author Alexander Kovriga
 */
public class FragmentList implements PaginatedList {
    
    private final AbstractFactoryDAO factoryDAO
            = FactoryCreator.getInstance().getFactory();
    
    private final int currentPage;
    private final int pageSize;

    public FragmentList(int currentPage, int pageSize) {
        this.currentPage = currentPage;
        this.pageSize = pageSize;
    }

    @Override
    public List getList() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getPageNumber() {
        return currentPage;
    }

    @Override
    public int getObjectsPerPage() {
        return pageSize;
    }

    @Override
    public int getFullListSize() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getSortCriterion() {return "";}

    @Override
    public SortOrderEnum getSortDirection() {return SortOrderEnum.ASCENDING;}

    @Override
    public String getSearchId() {return null;}

}
