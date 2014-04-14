/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.wind.manager;

import java.util.ResourceBundle;

/**
 *
 * @author Anatolii
 */
public class ConfigurationManager {

    private static final String FILE_PROPERTIES = "configuration";

    public static final String PAGE_LOGIN_ERROR = "PAGE_LOGIN_ERROR";

    private static final ConfigurationManager configurationManager = new ConfigurationManager();
    private final ResourceBundle bundle;

    private ConfigurationManager() {
        bundle = ResourceBundle.getBundle(FILE_PROPERTIES);
    }

    public static ConfigurationManager getInstance() {
        return configurationManager;
    }

    public String getProperty(String key) {
        return bundle.getString(key);
    }
}
