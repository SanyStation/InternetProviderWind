package com.netcracker.wind.manager;

import java.util.ResourceBundle;

/**
 *
 * @author Anatolii
 */
public class ConfigurationManager {

    private static final String FILE_PROPERTIES = "configuration";

    public static final String PAGE_LOGIN_ERROR = "PAGE_LOGIN_ERROR";
    public static final String REPORT_RI_MPR = "REPORT_RI_MPR";
    public static final String REPORT_RI_UNC = "REPORT_RI_UNC";
    public static final String REPORT_SI_O = "REPORT_SI_O";
    public static final String REPORT_SI_P = "REPORT_SI_P";

    private static final ConfigurationManager configurationManager
            = new ConfigurationManager();
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
