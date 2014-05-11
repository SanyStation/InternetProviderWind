package com.netcracker.wind.manager;

import java.util.MissingResourceException;
import java.util.ResourceBundle;
import org.apache.log4j.Logger;

/**
 *
 * @author Anatolii
 */
public class ConfigurationManager {

    private static final String FILE_PROPERTIES = "configuration";

    public static final String PAGE_LOGIN_ERROR = "PAGE_LOGIN_ERROR";
    public static final String PAGE_CONFIRM_ORDER = "PAGE_CONFIRM_ORDER";
    public static final String PAGE_LOGIN = "PAGE_LOGIN";
    public static final String REPORT_RI_MPR = "REPORT_RI_MPR";
    public static final String REPORT_RI_UNC = "REPORT_RI_UNC";
    public static final String REPORT_SI_O = "REPORT_SI_O";
    public static final String REPORT_SI_P = "REPORT_SI_P";
    public static final String REPORT_CIA_IPT = "REPORT_CIA_IPT";
    public static final String PAGE_PE_DASHBOARD = "PAGE_PE_DASHBOARD";
    public static final String PAGE_ERROR = "PAGE_ERROR";
    
    public static final String PAGE_ADM_ADD_USER = "PAGE_ADM_ADD_USER";
    public static final String PAGE_ADM_REVIEW_USER = "PAGE_ADM_REVIEW_USER";
    public static final String PAGE_ADM_USERS_LIST = "PAGE_ADM_USERS_LIST";
    
    public static final String PAGE_CSE_TASKS_LIST = "PAGE_CSE_TASKS_LIST";
    public static final String PAGE_CSE_SERVICE_ORDERS = "PAGE_CSE_SERVICE_ORDERS";
    public static final String PAGE_CSE_SERVICE_INSTANCES = "PAGE_CSE_SERVICE_INSTANCES";
    public static final String PAGE_CSE_ADD_CUSTOMER = "PAGE_CSE_ADD_CUSTOMER";
    public static final String PAGE_CSE_CUSTOMER_REVIEW = "PAGE_CSE_CUSTOMER_REVIEW";
    public static final String PAGE_CSE_USERS_LIST = "PAGE_CSE_USERS_LIST";
    public static final String PAGE_CSE_REPORT_SI_ORDERS = "PAGE_CSE_REPORT_SI_ORDERS";
    public static final String PAGE_CSE_REPORT_SI_PROFIT = "PAGE_CSE_REPORT_SI_PROFIT";
    public static final String PAGE_CSE_SELECTED_TASK = "PAGE_CSE_SELECTED_TASK";

    private static final ConfigurationManager configurationManager
            = new ConfigurationManager();
    private final ResourceBundle bundle;

    private static final Logger LOGGER
            = Logger.getLogger(ConfigurationManager.class.getName());

    private ConfigurationManager() {
        bundle = ResourceBundle.getBundle(FILE_PROPERTIES);
    }

    public static ConfigurationManager getInstance() {
        return configurationManager;
    }

    public String getProperty(String key) {
        String property = null;
        try {
            property = bundle.getString(key);
        } catch (MissingResourceException exception) {
            LOGGER.error(null, exception);
        }
        return property;
    }
}
