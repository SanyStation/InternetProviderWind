/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.wind.commands.implementations;

import com.netcracker.wind.commands.ICommand;
import com.netcracker.wind.manager.ConfigurationManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Anatolii
 */
public class ToPage implements ICommand {

    private static final String PAGE = "page";

    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String pageParameter = request.getParameter(PAGE);
        ConfigurationManager manager = ConfigurationManager.getInstance();
        String toPage = manager.getProperty(pageParameter);
        System.out.println(pageParameter + " = " + toPage);
        if(toPage == null){
            toPage = manager.getProperty(ConfigurationManager.PAGE_ERROR);
        }
        return toPage;
    }

}
