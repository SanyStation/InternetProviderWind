
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.wind.commands.implementations.order;

import com.netcracker.wind.commands.ICommand;
import com.netcracker.wind.manager.ConfigurationManager;
import com.netcracker.wind.paging.IExtendedPaginatedList;
import com.netcracker.wind.paging.TasksPaginatedList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Anatolii
 */
public class ListServiceInstance implements ICommand {

    public String execute(HttpServletRequest request, HttpServletResponse response) {
        IExtendedPaginatedList expl = new TasksPaginatedList(request,
                IExtendedPaginatedList.DEFAULT_PAGE_SIZE);
        request.getSession().setAttribute("tasks", expl);
        return ConfigurationManager.getInstance().
                getProperty(ConfigurationManager.PAGE_PE_DASHBOARD);
    }

}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.wind.commands.implementations.order;

import com.netcracker.wind.commands.ICommand;
import com.netcracker.wind.manager.ConfigurationManager;
import com.netcracker.wind.paging.IExtendedPaginatedList;
import com.netcracker.wind.paging.TasksPaginatedList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Anatolii
 */
public class ListServiceInstance implements ICommand {

    public String execute(HttpServletRequest request, HttpServletResponse response) {
        IExtendedPaginatedList expl = new TasksPaginatedList(request,
                IExtendedPaginatedList.DEFAULT_PAGE_SIZE);
        request.getSession().setAttribute("tasks", expl);
        return ConfigurationManager.getInstance().
                getProperty(ConfigurationManager.PAGE_PE_DASHBOARD);
    }

}
