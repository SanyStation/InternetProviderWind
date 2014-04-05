package com.netcracker.wind.reports.filefilters;

import java.io.File;
import javax.swing.filechooser.FileFilter;

/**
 *
 * @author Alexander Kovriga
 */
public class XlsFilter extends FileFilter {
    
    @Override
    public boolean accept(File f) {
        if (f.isDirectory()) {
            return true;
        }
        String extension = Utils.getExtension(f);
        if (extension != null) {
            return extension.equals(Utils.XLS);
        }
        return false;
    }
 
    @Override
    public String getDescription() {
        return "Excel file";
    }
    
}
