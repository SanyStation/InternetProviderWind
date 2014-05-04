/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.wind.paging;

import com.netcracker.wind.dao.factory.FactoryCreator;
import com.netcracker.wind.dao.interfaces.ITaskDAO;
import com.netcracker.wind.entities.Role;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Anatolii
 */
public class CSETasksPaginatedList extends AbstractPaginatedList {

    ITaskDAO taskDAO = FactoryCreator.getInstance().getFactory().createTaskDAO();

    public CSETasksPaginatedList(HttpServletRequest request, int pageSize) {
        super(request, pageSize);
    }

    public List getList() {
        return taskDAO.findByGroup(Role.CSE_GROUP_ID, pageNumber, pageSize);
    }

    public int getFullListSize() {
        return taskDAO.getRows();
    }

}
