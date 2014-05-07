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
 *
 * @author Alexander Kovriga
 */
public abstract class AbstractExcelExport extends ExcelHssfView {
    
    protected static final int DEFAULT_ID = 0;
    
    private static final Logger LOGGER
            = Logger.getLogger(AbstractExcelExport.class.getName());
    
    private final String templatePath;
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
    
    protected abstract Map fillBeansMap();
    
}
