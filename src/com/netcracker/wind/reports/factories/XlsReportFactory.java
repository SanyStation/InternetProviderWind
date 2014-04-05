package com.netcracker.wind.reports.factories;

import com.netcracker.wind.reports.interfaces.CiaReport;
import com.netcracker.wind.reports.interfaces.RiReport;
import com.netcracker.wind.reports.interfaces.SiReport;
import com.netcracker.wind.reports.xls.XlsCiaReport;
import com.netcracker.wind.reports.xls.XlsRiReport;
import com.netcracker.wind.reports.xls.XlsSiReport;

/**
 *
 * @author Alexander Kovriga
 */
public class XlsReportFactory extends ReportFactory {

    @Override
    public CiaReport getCiaReport() {
        return new XlsCiaReport();
    }

    @Override
    public RiReport getRiReport() {
        return new XlsRiReport();
    }

    @Override
    public SiReport getSiReport() {
        return new XlsSiReport();
    }
    
}
