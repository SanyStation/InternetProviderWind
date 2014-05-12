package com.netcracker.wind.commands;

import com.netcracker.wind.manager.ConfigurationManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This interface is common interface for commands. All classes that implement
 * this interface can be invoked in main controller.
 *
 * @author Bed Anatolii
 *
 */
public interface ICommand {

    public ConfigurationManager manager = ConfigurationManager.getInstance();

    /**
     *
     * @param request servlet request
     * @param response servlet response
     * @return next page if it's not AJAX request or answer in text format if
     * it's AJAX(protocol for message can be any JSON, XML...)
     */
    public String execute(HttpServletRequest request,
            HttpServletResponse response);
}
