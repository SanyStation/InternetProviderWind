/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.wind.paging;

import com.netcracker.wind.dao.factory.FactoryCreator;
import com.netcracker.wind.dao.interfaces.ITaskDAO;
import com.netcracker.wind.dao.interfaces.IUserDAO;
import com.netcracker.wind.entities.Role;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Anatolii
 */
public class CSEUsersPaginatedList extends AbstractPaginatedList {

    IUserDAO userDAO = FactoryCreator.getInstance().getFactory().createUserDAO();

    public CSEUsersPaginatedList(HttpServletRequest request, int pageSize) {
        super(request, pageSize);
    }

    public List getList() {
        System.out.println("useruser");
        return userDAO.findByRole(Role.CU_GROUP_ID, pageNumber, pageSize);
    }

    public int getFullListSize() {
        return userDAO.getRows();
    }

}
