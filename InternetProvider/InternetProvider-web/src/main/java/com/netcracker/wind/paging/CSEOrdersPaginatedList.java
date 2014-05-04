/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.netcracker.wind.paging;

import com.netcracker.wind.dao.factory.FactoryCreator;
import com.netcracker.wind.dao.interfaces.IServiceOrderDAO;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Oksana
 */
public class CSEOrdersPaginatedList extends AbstractPaginatedList{
       IServiceOrderDAO orderDAO = FactoryCreator.getInstance().getFactory().createServiceOrderDAO();

    public CSEOrdersPaginatedList(HttpServletRequest request, int pageSize) {
        super(request, pageSize);
    }
    public List getList() {
       System.out.println("useruser");
        return orderDAO.findAll(pageNumber, pageSize);
    }

    public int getFullListSize() {
      return orderDAO.getRows();
    }
    
}
