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
public class TasksByPerformerStatusPaginatedList extends AbstractPaginatedList{
     private int performerId;
     private String status;
    ITaskDAO taskDAO = FactoryCreator.getInstance().getFactory().createTaskDAO();

    public TasksByPerformerStatusPaginatedList(HttpServletRequest request, int pageSize) {
        super(request, pageSize);
    }

    public List getList() {
         return taskDAO.findByPerformerStatus(performerId, status, pageNumber, pageSize);
    }

    public int getFullListSize() {
        return taskDAO.getRows();
    }
    
     public TasksByPerformerStatusPaginatedList setPerformer(int performerId){
        this.performerId=performerId;
        return this;
    }
     
     public TasksByPerformerStatusPaginatedList setStatus(String status){
        this.status=status;
        return this;
    }
}
