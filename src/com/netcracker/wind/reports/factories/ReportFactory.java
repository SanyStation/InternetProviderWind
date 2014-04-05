package com.netcracker.wind.reports.factories;

import com.netcracker.wind.reports.interfaces.CiaReport;
import com.netcracker.wind.reports.interfaces.RiReport;
import com.netcracker.wind.reports.interfaces.SiReport;

/**
 *
 * @author Alexander Kovriga
 */
public abstract class ReportFactory {

    public static final int XLS = 0;
    public static final int CSV = 1;
    
    public abstract CiaReport getCiaReport();
    public abstract RiReport getRiReport();
    public abstract SiReport getSiReport();

    public static ReportFactory getReportFactory(int type) {
        switch (type) {
            case 0:
                return new XlsReportFactory();
            case 1:
                return new CsvReportFactory();
        }
        return null;
    }

}
