package com.netcracker.wind.commands.implementations;

import com.netcracker.wind.commands.ICommand;
import com.netcracker.wind.manager.ConfigurationManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This class-command was designed for situation when will request to controller
 * with wrong key for parameter command. Command do redirect to index page.
 *
 * @author Bed Anatolii
 */
public class NoCommand implements ICommand {

    public String execute(HttpServletRequest request,
            HttpServletResponse response) {
        return manager.getProperty(ConfigurationManager.PAGE_INDEX);
    }

}
