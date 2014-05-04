/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.netcracker.wind.paging;

import com.netcracker.wind.dao.factory.FactoryCreator;
import com.netcracker.wind.dao.interfaces.ITaskDAO;
import com.netcracker.wind.entities.Role;
import com.netcracker.wind.entities.Task;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Oksana
 */
public class TaskPaginationList extends AbstractPaginatedList{
    private int groupId;
    ITaskDAO taskDAO = FactoryCreator.getInstance().getFactory().createTaskDAO();
      public TaskPaginationList(HttpServletRequest request, int pageSize) {
        super(request, pageSize);
    }

    public List getList() {
          System.out.println("useruser");
        return taskDAO.findByGroupStatus(groupId, Task.Status.NEW.toString(), pageNumber, pageSize);
    }

    public int getFullListSize() {
         return taskDAO.getRows();
    }
    public TaskPaginationList setGroup(int groupId){
        this.groupId=groupId;
        return this;
    }
    
}
