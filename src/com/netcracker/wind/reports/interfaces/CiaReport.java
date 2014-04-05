package com.netcracker.wind.reports.interfaces;

import java.io.File;

/**
 *
 * @author Alexander Kovriga
 */
public interface CiaReport {
    
    public static final String SEPARATOR = File.separator;
    public static final String PATH = "templates" + SEPARATOR + "cia" 
            + SEPARATOR;
    
    public void generateIPTReport();
    
}
