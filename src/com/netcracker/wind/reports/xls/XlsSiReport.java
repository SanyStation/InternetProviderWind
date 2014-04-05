package com.netcracker.wind.reports.xls;

import com.netcracker.wind.reports.filefilters.XlsFilter;
import com.netcracker.wind.reports.interfaces.SiReport;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import net.sf.jxls.exception.ParsePropertyException;
import net.sf.jxls.transformer.XLSTransformer;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

/**
 *
 * @author Alexander Kovriga
 */
public class XlsSiReport implements SiReport {
    
    private static final String TEMPLATE_NEW_ORDERS_PER_PERIOD = 
            "template_si_new_ord_per_period.xls";
    private static final String TEMPLATE_PROFITABILITY_BY_MONTH =
            "template_si_prof_by_month.xls";
    private static final String TEMPLATE_DISC_ORDERS_PER_PERIOD =
            "template_si_disc_ord_per_period.xls";
    
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
    public void generateNewOrdersPerPeriodReport(List orders,
            Calendar periodFrom, Calendar periodTo) {
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        Map map = new HashMap();
        map.put("periodFrom", format.format(periodFrom.getTime()));
        map.put("periodTo", format.format(periodTo.getTime()));
        map.put("dateFrom", map);
        map.put("orders", orders);
        try {
            export(map, SiReport.PATH + TEMPLATE_NEW_ORDERS_PER_PERIOD);
        } catch (ParsePropertyException | IOException | 
                InvalidFormatException ex) {
            Logger.getLogger(XlsRiReport.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void generateProfitabilityByMonthReport(List routers, Calendar month) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void generateDisconnectOrdersPerPeriodReport(List orders, Calendar dateFrom, Calendar dateTo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
