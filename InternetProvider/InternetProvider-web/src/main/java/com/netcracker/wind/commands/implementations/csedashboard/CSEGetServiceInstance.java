/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.netcracker.wind.commands.implementations.csedashboard;

import com.netcracker.wind.commands.ICommand;
import com.netcracker.wind.dao.implementations.helper.AbstractOracleDAO;
import com.netcracker.wind.paging.IExtendedPaginatedList;
import com.netcracker.wind.paging.ServiceInstancePaginationList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Oksana
 */
public class CSEGetServiceInstance implements ICommand {


    private static final String SI = "service_instances";

    public String execute(HttpServletRequest request,
            HttpServletResponse response) {
        IExtendedPaginatedList paginatedList = new ServiceInstancePaginationList(request, 
                AbstractOracleDAO.DEFAULT_PAGE_SIZE);
        HttpSession session = request.getSession(false);
        if(session == null){
            return "";
        }
        session.setAttribute(SI, paginatedList);
        return "/WEB-INF/cse/cse-page-service-instances.jsp";
    }
    
}
