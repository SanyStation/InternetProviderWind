/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.netcracker.wind.paging;

import com.netcracker.wind.dao.factory.FactoryCreator;
import com.netcracker.wind.dao.interfaces.IServiceInstanceDAO;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Oksana
 */
public class SIUserPaginationList extends AbstractPaginatedList {
    private int performerId;

    private final IServiceInstanceDAO serviceInstanceDAO = FactoryCreator.getInstance().getFactory()
            .createServiceInstanceDAO();

    public SIUserPaginationList(HttpServletRequest request, int pageSize) {
        super(request, pageSize);
    }
    
    @Override
    public List getList() {
        return serviceInstanceDAO.findByUser(performerId, pageNumber, pageSize);
    }
    
    @Override
    public int getFullListSize() {
        return serviceInstanceDAO.countRows();
    }
     public SIUserPaginationList setPerformer(int performerId) {
        this.performerId = performerId;
        return this;
    }
}
