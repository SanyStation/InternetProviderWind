/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.netcracker.wind.paging;

import com.netcracker.wind.dao.factory.FactoryCreator;
import com.netcracker.wind.dao.interfaces.ITaskDAO;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Oksana
 */
public class OwnTasksPaginatedList  extends AbstractPaginatedList{
    private int performerID;
    ITaskDAO taskDAO = FactoryCreator.getInstance().getFactory().createTaskDAO();
      public OwnTasksPaginatedList(HttpServletRequest request, int pageSize) {
        super(request, pageSize);
    }

    public List getList() {
          System.out.println("useruser");
        return taskDAO.findByPerformer(performerID, pageNumber, pageSize);
    }

    public int getFullListSize() {
         return taskDAO.getRows();
    }
    public OwnTasksPaginatedList setPerformer(int performerId){
        this.performerID=performerId;
        return this;
    }
    
}
