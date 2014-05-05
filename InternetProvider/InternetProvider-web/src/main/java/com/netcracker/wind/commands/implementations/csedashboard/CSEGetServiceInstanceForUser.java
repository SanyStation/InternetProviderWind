/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.netcracker.wind.commands.implementations.csedashboard;

import com.netcracker.wind.commands.ICommand;
import com.netcracker.wind.entities.Role;
import com.netcracker.wind.entities.User;
import com.netcracker.wind.paging.IExtendedPaginatedList;
import com.netcracker.wind.paging.SIUserPaginationList;
import com.netcracker.wind.paging.ServiceInstancePaginationList;
import com.netcracker.wind.paging.TaskPaginationList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Oksana
 */
public class CSEGetServiceInstanceForUser implements ICommand{
    private final String SI="service_instances";

    public String execute(HttpServletRequest request, HttpServletResponse response) {
       
    
        HttpSession session = request.getSession(false);
        if(session == null){
            return "";
        }
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "";
        }
        int performerId = user.getId();
          IExtendedPaginatedList paginatedList = new SIUserPaginationList(request, 
                IExtendedPaginatedList.DEFAULT_PAGE_SIZE).setPerformer(performerId);
        session.setAttribute(SI, paginatedList);
        return "/WEB-INF/cse/cse-page-service-instances.jsp";

    }
    
}
