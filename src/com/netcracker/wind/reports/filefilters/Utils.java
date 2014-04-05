package com.netcracker.wind.reports.filefilters;

import java.io.File;

/**
 *
 * @author Alexander Kovriga
 */
public class Utils {
    
    public final static String XLS = "xls";
    
    public static String getExtension(File f) {
        String ext = null;
        String s = f.getName();
        int i = s.lastIndexOf('.');
        if (i > 0 &&  i < s.length() - 1) {
            ext = s.substring(i+1).toLowerCase();
        }
        return ext;
    }
    
}
