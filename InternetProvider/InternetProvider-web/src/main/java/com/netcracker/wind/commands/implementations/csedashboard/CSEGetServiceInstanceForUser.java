/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.wind.commands.implementations.csedashboard;

import com.netcracker.wind.annotations.RolesAllowed;
import com.netcracker.wind.commands.ICommand;
import com.netcracker.wind.dao.implementations.helper.AbstractOracleDAO;
import com.netcracker.wind.entities.Role;
import com.netcracker.wind.paging.IExtendedPaginatedList;
import com.netcracker.wind.paging.SIUserPaginationList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Oksana
 */
@RolesAllowed(roles = Role.Roles.CustomerSupportEngineer)
public class CSEGetServiceInstanceForUser implements ICommand {

    private static final String SI = "service_instances";
    private static final String CUSTOMER_ID = "customer_id";

    public String execute(HttpServletRequest request, HttpServletResponse response) {

        HttpSession session = request.getSession(false);
        if (session == null) {
            return "";
        }
        
        int customerId = Integer.parseInt(request.getParameter(CUSTOMER_ID));
        IExtendedPaginatedList paginatedList = new SIUserPaginationList(request,
                AbstractOracleDAO.DEFAULT_PAGE_SIZE).setPerformer(customerId);
        session.setAttribute(SI, paginatedList);
        return "/WEB-INF/cse/cse-page-service-instances.jsp";

    }

}
