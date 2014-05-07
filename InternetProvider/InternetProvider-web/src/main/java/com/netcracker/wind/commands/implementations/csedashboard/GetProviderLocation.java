/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.wind.commands.implementations.csedashboard;

import com.netcracker.wind.commands.ICommand;
import com.netcracker.wind.dao.implementations.helper.AbstractOracleDAO;
import com.netcracker.wind.paging.IExtendedPaginatedList;
import com.netcracker.wind.paging.ProviderLocationPaginationList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Anna
 */
public class GetProviderLocation implements ICommand {

    private static final String PROVIDER_LOCATIONS = "provider_locations";

    public String execute(HttpServletRequest request, HttpServletResponse response) {
        IExtendedPaginatedList paginatedList = new ProviderLocationPaginationList(request,
                AbstractOracleDAO.DEFAULT_PAGE_SIZE);
        HttpSession session = request.getSession(false);
        if (session == null) {
            return "";
        }
        session.setAttribute(PROVIDER_LOCATIONS, paginatedList);
        return "/WEB-INF/cse/?.jsp";
    }
}
