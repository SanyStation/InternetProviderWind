package com.netcracker.wind.reports;

/**
 * The {@code ReportFactory} produces the heirs of the {@code Report}.
 * 
 * @author Alexander Kovriga
 */
public class ReportFactory {

    public static final int CSV = 0;
    public static final int XLS = 1;

    /**
     * Creates the heirs of {@code Report}.
     * 
     * @param type the type of report. Recommended uses constants that 
     * predefined in this class
     * @return the concrete report
     */
    public static Report getReport(int type) {
        switch (type) {
            case 0:
                return new CsvReport();
            case 1:
                return new XlsReport();
            default:
                return null;
        }
    }

}
