package com.netcracker.wind.reports.csv;

import com.netcracker.wind.reports.interfaces.SiReport;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author Alexander Kovriga
 */
public class CsvSiReport implements SiReport {

    @Override
    public void generateNewOrdersPerPeriodReport(List orders, Calendar dateFrom, Calendar dateTo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void generateProfitabilityByMonthReport(List routers, Calendar month) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void generateDisconnectOrdersPerPeriodReport(List orders, Calendar dateFrom, Calendar dateTo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
