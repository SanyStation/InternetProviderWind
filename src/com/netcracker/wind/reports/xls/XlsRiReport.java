package com.netcracker.wind.reports.xls;

import com.netcracker.wind.reports.filefilters.XlsFilter;
import com.netcracker.wind.reports.interfaces.RiReport;
import java.io.IOException;
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
public class XlsRiReport implements RiReport {

    private static final String TEMPLATE_ROUTERS_UTIL_N_CAP
            = "template_ri_routers_util_n_cap.xls";
    private static final String TEMPLATE_MOST_PROF_ROUTER
            = "template_most_prof_router.xls";

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
    public void generateRoutersUtilizationAndCapacityReport(List routers) {
        Map map = new HashMap();
        map.put("routers", routers);
        try {
            export(map, RiReport.PATH + TEMPLATE_ROUTERS_UTIL_N_CAP);
        } catch (ParsePropertyException | IOException |
                InvalidFormatException ex) {
            Logger.getLogger(XlsRiReport.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void generateMostProfitbleRouterReport(List routers) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
