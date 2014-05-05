/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.wind.paging;

import com.netcracker.wind.dao.factory.FactoryCreator;
import com.netcracker.wind.dao.interfaces.IProviderLocationDAO;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Anna
 */
public class ProviderLocationPaginationList extends AbstractPaginatedList {

    private final IProviderLocationDAO providerLocationDAO = FactoryCreator.getInstance().getFactory()
            .createProviderLocationDAO();

    public ProviderLocationPaginationList(HttpServletRequest request, int pageSize) {
        super(request, pageSize);
    }

    public List getList() {
      return  providerLocationDAO.findAll(pageNumber, pageSize);
    }

    public int getFullListSize() {
         return  providerLocationDAO.countRows();
    }

}
