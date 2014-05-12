package com.netcracker.wind.commands.implementations;

import com.netcracker.wind.commands.ICommand;
import com.netcracker.wind.manager.ConfigurationManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Bed Anatolii
 */
public class NoCommand implements ICommand {

    public String execute(HttpServletRequest request,
            HttpServletResponse response) {
        return manager.getProperty(ConfigurationManager.PAGE_INDEX);
    }

}
