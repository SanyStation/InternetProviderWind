package com.netcracker.wind.reports.factories;

import com.netcracker.wind.reports.csv.CsvCiaReport;
import com.netcracker.wind.reports.csv.CsvRiReport;
import com.netcracker.wind.reports.csv.CsvSiReport;
import com.netcracker.wind.reports.interfaces.CiaReport;
import com.netcracker.wind.reports.interfaces.RiReport;
import com.netcracker.wind.reports.interfaces.SiReport;

/**
 *
 * @author Alexander Kovriga
 */
public class CsvReportFactory extends ReportFactory {

    @Override
    public CiaReport getCiaReport() {
        return new CsvCiaReport();
    }

    @Override
    public RiReport getRiReport() {
        return new CsvRiReport();
    }

    @Override
    public SiReport getSiReport() {
        return new CsvSiReport();
    }
    
}
