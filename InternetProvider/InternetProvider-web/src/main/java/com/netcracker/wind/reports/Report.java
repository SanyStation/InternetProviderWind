package com.netcracker.wind.reports;

import java.io.File;
import java.util.Calendar;
import java.util.List;

/**
 * The {@code Report} interface provides all types of reports for further 
 * implementation.
 * 
 * @author Alexander Kovriga
 */
public interface Report {
    
    public static final String SEPARATOR = File.separator;
    
    public void generateCiaImpactPropagationTreeReport();
    public void generateRiUtilizationAndCapacityReport(List router);
    public void generateRiMostProfitableRouterReport();
    public void generateSiNewOrdersPerPeriodReport(List orders, 
            Calendar periodFrom, Calendar periodTo);
    public void generateSiProfitabilityByMonthReport();
    public void generateSiDisconnectedOrdersPerPeriodReport();
    
}
