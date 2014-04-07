package com.netcracker.wind.reports;

import java.util.Calendar;
import java.util.List;

/**
 * The {@code CsvReport} designed to implement methods of {@code Report} 
 * interface for generation reports in txt (plain text file).
 * 
 * @author Alexander Kovriga
 */
class CsvReport implements Report {

    @Override
    public void generateCiaImpactPropagationTreeReport() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void generateRiUtilizationAndCapacityReport(List routers) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void generateRiMostProfitableRouterReport() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void generateSiNewOrdersPerPeriodReport(List orders,
            Calendar periodFrom, Calendar periodTo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void generateSiProfitabilityByMonthReport() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void generateSiDisconnectedOrdersPerPeriodReport() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
}
