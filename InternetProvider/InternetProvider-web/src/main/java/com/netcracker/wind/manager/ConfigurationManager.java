package com.netcracker.wind.manager;

import java.util.MissingResourceException;
import java.util.ResourceBundle;
import org.apache.log4j.Logger;

/**
 * This class was designed for ease work with pages. Page must persist in
 * properties file
 * {@code private static final String FILE_PROPERTIES = "configuration";} as key
 * = value. All keys must be defined as public constants in this class.
 *
 * @author Anatolii
 */
public class ConfigurationManager {

    private static final String FILE_PROPERTIES = "configuration";

    public static final String PAGE_LOGIN = "PAGE_LOGIN";
    public static final String PAGE_INDEX = "PAGE_INDEX";
    public static final String PAGE_PROFILE = "PAGE_PROFILE";
    public static final String PAGE_BLOCKED = "PAGE_BLOCKED";
    public static final String PAGE_ERROR = "PAGE_ERROR";
    public static final String PAGE_WRON_SELECTED_TASK = "PAGE_WRON_SELECTED_TASK";

    public static final String PAGE_ADM_ADD_USER = "PAGE_ADM_ADD_USER";
    public static final String PAGE_ADM_REVIEW_USER = "PAGE_ADM_REVIEW_USER";
    public static final String PAGE_ADM_USERS_LIST = "PAGE_ADM_USERS_LIST";

    public static final String PAGE_CSE_TASKS_LIST = "PAGE_CSE_TASKS_LIST";
    public static final String PAGE_CSE_SERVICE_ORDERS = "PAGE_CSE_SERVICE_ORDERS";
    public static final String PAGE_CSE_REVIEW_ORDER = "PAGE_CSE_REVIEW_ORDER";
    public static final String PAGE_CSE_SERVICE_INSTANCES = "PAGE_CSE_SERVICE_INSTANCES";
    public static final String PAGE_CSE_REVIEW_INSTANCE = "PAGE_CSE_REVIEW_INSTANCE";
    public static final String PAGE_CSE_ADD_CUSTOMER = "PAGE_CSE_ADD_CUSTOMER";
    public static final String PAGE_CSE_CUSTOMER_REVIEW = "PAGE_CSE_CUSTOMER_REVIEW";
    public static final String PAGE_CSE_USERS_LIST = "PAGE_CSE_USERS_LIST";
    public static final String PAGE_CSE_REPORT_SI_ORDERS = "PAGE_CSE_REPORT_SI_ORDERS";
    public static final String PAGE_CSE_REPORT_SI_PROFIT = "PAGE_CSE_REPORT_SI_PROFIT";
    public static final String PAGE_CSE_SELECTED_TASK = "PAGE_CSE_SELECTED_TASK";

    public static final String PAGE_CU_PAGE_INSTANCES_LIST = "PAGE_CU_PAGE_INSTANCES_LIST";
    public static final String PAGE_CU_PAGE_ORDERS_LIST = "PAGE_CU_PAGE_ORDERS_LIST";
    public static final String PAGE_CU_PAGE_REVIEW_INSTANCE = "PAGE_CU_PAGE_REVIEW_INSTANCE";
    public static final String PAGE_CU_PAGE_REVIEW_ORDER = "PAGE_CU_PAGE_REVIEW_ORDER";

    public static final String PAGE_IE_SELECTED_TASK = "PAGE_IE_SELECTED_TASK";
    public static final String PAGE_IE_REPORT_RI_PROFIT = "PAGE_IE_REPORT_RI_PROFIT";
    public static final String PAGE_IE_REPORT_RI_UTIL = "PAGE_IE_REPORT_RI_UTIL";
    public static final String PAGE_IE_TASKS_LIST = "PAGE_IE_TASKS_LIST";

    public static final String PAGE_PE_TASKS_LIST = "PAGE_PE_TASKS_LIST";
    public static final String PAGE_PE_SELECTED_TASK = "PAGE_PE_SELECTED_TASK";
    public static final String PAGE_PE_REPORT_CIA_IPT = "PAGE_PE_REPORT_CIA_IPT";
    public static final String PAGE_PE_REVIEW_CIRCUIT = "PAGE_PE_REVIEW_CIRCUIT";
    public static final String PAGE_PE_REVIEW_DEVICE = "PAGE_PE_REVIEW_DEVICE";
    public static final String PAGE_PE_REVIEW_PORT = "PAGE_PE_REVIEW_PORT";
    public static final String PAGE_PE_REVIEW_INSTANCE = "PAGE_PE_REVIEW_INSTANCE";

    private static final ConfigurationManager configurationManager
            = new ConfigurationManager();
    private final ResourceBundle bundle;

    private static final Logger LOGGER
            = Logger.getLogger(ConfigurationManager.class.getName());

    private ConfigurationManager() {
        bundle = ResourceBundle.getBundle(FILE_PROPERTIES);
    }

    /**
     * Method return instance of singleton class {@link ConfigurationManager}
     *
     * @return instance of class {@link ConfigurationManager}
     */
    public static ConfigurationManager getInstance() {
        return configurationManager;
    }

    /**
     * Method allows to get property by key.
     *
     * @param key key for needed property.
     * @return property under pointed key or null if such key not exist in
     * property file.
     */
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
