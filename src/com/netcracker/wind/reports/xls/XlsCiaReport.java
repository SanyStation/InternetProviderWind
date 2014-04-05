package com.netcracker.wind.reports.xls;

import com.netcracker.wind.reports.filefilters.XlsFilter;
import com.netcracker.wind.reports.interfaces.CiaReport;
import java.io.IOException;
import java.util.Map;
import javax.swing.JFileChooser;
import net.sf.jxls.exception.ParsePropertyException;
import net.sf.jxls.transformer.XLSTransformer;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

/**
 *
 * @author Alexander Kovriga
 */
public class XlsCiaReport implements CiaReport {
    
    private void export(Map beans, String template)
            throws ParsePropertyException, IOException, InvalidFormatException {
        XLSTransformer xlst = new XLSTransformer();
        JFileChooser jfc = new JFileChooser();
        jfc.addChoosableFileFilter(new XlsFilter());
        jfc.setAcceptAllFileFilterUsed(true);
        if (jfc.showSaveDialog(null) == 0) {
            String destination = jfc.getSelectedFile().getAbsolutePath();
            xlst.transformXLS(template, beans, destination);
        }
    }

    @Override
    public void generateIPTReport() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
