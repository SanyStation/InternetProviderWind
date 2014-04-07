package com.netcracker.wind.reports;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.jxls.exception.ParsePropertyException;
import net.sf.jxls.transformer.XLSTransformer;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

/**
 * The {@code XlsReport} designed to implement methods of {@code Report}
 * interface for generation reports in xls (excel spreadsheets) format from
 * predefined templates.
 *
 * @see Report
 * @see <a href="http://jxls.sourceforge.net/">About JXLS</a>
 *
 * @author Alexander Kovriga
 */
class XlsReport implements Report {

    public static final String PATH = "templates" + SEPARATOR;
    public static final String DESTINATION_PATH = "reports" + SEPARATOR;
    private static final String TEMPLATE_SI_NEW_ORDERS_PER_PERIOD
            = "template_si_new_ord_per_period.xls";
    private static final String TEMPLATE_SI_PROFITABILITY_BY_MONTH
            = "template_si_prof_by_month.xls";
    private static final String TEMPLATE_SI_DISC_ORDERS_PER_PERIOD
            = "template_si_disc_ord_per_period.xls";
    private static final String TEMPLATE_RI_ROUTERS_UTIL_N_CAP
            = "template_ri_routers_util_n_cap.xls";
    private static final String TEMPLATE_RI_MOST_PROF_ROUTER
            = "template_ri_most_prof_router.xls";
    private static final String TEMPLATE_CIA_IMPACT_PROPAGATION_TREE
            = "template_cia_ipt.xls";

    /**
     * Generates name of destination file from name of template and current date
     * and time.
     *
     * @param template the predefined template name that must have following
     * format: template_<type>_<name>.xls. Example: template_cia_ipt.xls
     * @return the name of destination file in the following format:
     * <type>_<name>_<date and time>.xls. Example:
     * cia_ipt_2014_04_06_13_27_24.xls
     */
    private String generateDestination(String template) {
        String destination = template.substring(template.indexOf("_") + 1,
                template.indexOf(".xls"));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
        destination += "_" + sdf.format(Calendar.getInstance().getTime())
                + ".xls";
        return destination;
    }

    /**
     * Exports the particular data to xls (excel spreadsheets) format via
     * XLSTransormer. Method transformXLS(String templatePath, Map beans, String
     * resultPath) accepts the path to template file, map of beans (java beans
     * that should be exported to xls file) and result file path. In result will
     * be generated the xls file with particular data.
     *
     * @param template the path to template file
     * @param beans the data that designed to export
     */
    private void export(String template, Map beans) {
        try {
            new XLSTransformer().transformXLS(PATH + template, beans,
                    DESTINATION_PATH + generateDestination(template));
        } catch (ParsePropertyException ppe) {
        } catch (InvalidFormatException ex) {
        } catch (IOException ioe) {
            Logger.getLogger(XlsReport.class.getName())
                    .log(Level.SEVERE, null, ioe);
        }
    }

    @Override
    public void generateCiaImpactPropagationTreeReport() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void generateRiUtilizationAndCapacityReport(List routers) {
        Map map = new HashMap();
        map.put("routers", routers);
        export(TEMPLATE_RI_ROUTERS_UTIL_N_CAP, map);
    }

    @Override
    public void generateRiMostProfitableRouterReport() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void generateSiNewOrdersPerPeriodReport(List orders,
            Calendar periodFrom, Calendar periodTo) {
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        Map map = new HashMap();
        map.put("periodFrom", format.format(periodFrom.getTime()));
        map.put("periodTo", format.format(periodTo.getTime()));
        map.put("dateFrom", map);
        map.put("orders", orders);
        export(TEMPLATE_SI_NEW_ORDERS_PER_PERIOD, map);
    }

    @Override
    public void generateSiProfitabilityByMonthReport() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void generateSiDisconnectedOrdersPerPeriodReport() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
