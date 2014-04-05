package com.netcracker.wind.reports.interfaces;

import com.netcracker.wind.reports.test.Router;
import java.io.File;
import java.util.List;

/**
 *
 * @author Alexander Kovriga
 */
public interface RiReport {
    
    public static final String SEPARATOR = File.separator;
    public static final String PATH = "templates" + SEPARATOR + "ri" 
            + SEPARATOR;
    
    public void generateRoutersUtilizationAndCapacityReport(List routers);
    public void generateMostProfitbleRouterReport(List routers);
    
}
