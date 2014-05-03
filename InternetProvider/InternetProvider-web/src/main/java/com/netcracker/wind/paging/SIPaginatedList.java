
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.wind.paging;

import com.netcracker.wind.dao.factory.AbstractFactoryDAO;
import com.netcracker.wind.dao.factory.FactoryCreator;
import com.netcracker.wind.dao.interfaces.IServiceInstanceDAO;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Anatolii
 */
public class SIPaginatedList extends AbstractPaginatedList {

    private final IServiceInstanceDAO serviceInstanceDAO
            = FactoryCreator.getInstance().getFactory().createServiceInstanceDAO();

    public SIPaginatedList(HttpServletRequest request, int pageSize) {
        super(request, pageSize);
    }

    public List getList() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getFullListSize() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
