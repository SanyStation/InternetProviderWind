package com.netcracker.wind.commands;

import com.netcracker.wind.manager.ConfigurationManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Bed Anatolii
 */
public interface ICommand {
    
    public ConfigurationManager manager = ConfigurationManager.getInstance();
    
    public String execute(HttpServletRequest request,
            HttpServletResponse response);
}
