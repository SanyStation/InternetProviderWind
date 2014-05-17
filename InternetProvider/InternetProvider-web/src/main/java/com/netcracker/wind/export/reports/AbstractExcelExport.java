package com.netcracker.wind.export.reports;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;
import javax.servlet.jsp.JspException;
import net.sf.jxls.transformer.XLSTransformer;
import org.apache.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;
import org.displaytag.export.excel.ExcelHssfView;
import org.displaytag.model.TableModel;

/**
 * The class {@code AbstractExcelExport} encapsulates the method
 * {@code doExoprt} that writes received from subclasses map of beans to Excel 
 * file using JXLS library.
 * 
 * @author Alexander Kovriga
 */
public abstract class AbstractExcelExport extends ExcelHssfView {
    
    private static final Logger LOGGER
            = Logger.getLogger(AbstractExcelExport.class.getName());
    
    /**
     * This id using for filling the entities that needed to set id but didn't 
     * attach to database.
     */
    protected static final int DEFAULT_ID = 0;
    
    /**
     * Path to excel template file.
     */
    private final String templatePath;
    
    /**
     * TableModel contains all data from table that will be exported to excel
     * file.
     */
    protected TableModel tableModel;

    public AbstractExcelExport(String templatePath) {
        this.templatePath = templatePath;
    }

    @Override
    public void setParameters(TableModel tableModel, boolean exportFullList,
            boolean includeHeader, boolean decorateValues) {
        super.setParameters(tableModel, exportFullList, includeHeader,
                decorateValues);
        this.tableModel = tableModel;
    }
    
    @Override
    public void doExport(OutputStream out)
            throws JspException {
        InputStream is = null;
        try {
            is = getClass().getResourceAsStream(templatePath);
            Map beans = fillBeansMap();
            Workbook wb = new XLSTransformer().transformXLS(is, beans);
            wb.write(out);
        } catch (InvalidFormatException ex) {
            LOGGER.error(null, ex);
        } catch (IOException ex) {
            LOGGER.error(null, ex);
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
            } catch (IOException ex) {
                LOGGER.error(null, ex);
            }
        }
    }
    
    /**
     * Fills the map of beans for exporting to excel file that using in JXLS
     * library.
     * 
     * @return map of java beans
     */
    protected abstract Map fillBeansMap();
    
}
