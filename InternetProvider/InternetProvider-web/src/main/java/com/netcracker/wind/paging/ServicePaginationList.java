/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.wind.paging;

import com.netcracker.wind.dao.factory.FactoryCreator;
import com.netcracker.wind.dao.interfaces.IServiceDAO;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Anna
 */
public class ServicePaginationList extends AbstractPaginatedList {

    private final IServiceDAO serviceDAO = FactoryCreator.getInstance().getFactory()
            .createServiceDAO();

    public ServicePaginationList(HttpServletRequest request, int pageSize) {
        super(request, pageSize);
    }

    public List getList() {
        return serviceDAO.findAll(pageNumber, pageSize);
    }

    public int getFullListSize() {
        return serviceDAO.countRows();
    }

}
