package com.netcracker.wind.reports.interfaces;

import java.io.File;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author Alexander Kovriga
 */
public interface SiReport {
    
    public static final String SEPARATOR = File.separator;
    public static final String PATH = "templates" + SEPARATOR + "si" 
            + SEPARATOR;
    
    public void generateNewOrdersPerPeriodReport(List orders,
            Calendar periodFrom, Calendar periodTo);
    public void generateProfitabilityByMonthReport(List routers,
            Calendar month);
    public void generateDisconnectOrdersPerPeriodReport(List orders,
            Calendar dateFrom, Calendar dateTo);
    
}
