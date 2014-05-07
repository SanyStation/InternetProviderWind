package com.netcracker.wind.export.reports;

import com.netcracker.wind.entities.reports.SiProfit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import org.displaytag.exception.DecoratorException;
import org.displaytag.exception.ObjectLookupException;
import org.displaytag.model.Column;
import org.displaytag.model.ColumnIterator;
import org.displaytag.model.Row;
import org.displaytag.model.RowIterator;

/**
 *
 * @author Alexander Kovriga
 */
public class SiProfitExport extends AbstractExcelExport {

    private static final Logger LOGGER = Logger.getLogger(
            SiProfitExport.class.getName());

    private static final String TEMPLATE_PATH
            = "/reports/templates/template_si_profit.xls";

    public SiProfitExport() {
        super(TEMPLATE_PATH);
    }

    @Override
    protected Map fillBeansMap() {
        List<SiProfit> profits = new ArrayList<SiProfit>();
        Map beans = new HashMap();
        RowIterator rowIterator = super.tableModel.getRowIterator(true);
        try {
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                ColumnIterator columnIterator = row.getColumnIterator(
                        super.tableModel.getHeaderCellList());
                Column column;
                column = columnIterator.nextColumn();
                String providerLocationName = (String) column.getValue(true);
                column = columnIterator.nextColumn();
                String serviceName = (String) column.getValue(true);
                column = columnIterator.nextColumn();
                double sum = (Double) column.getValue(true);
                
                SiProfit profit = new SiProfit();
                profit.setProviderLocationName(providerLocationName);
                profit.setServiceName(serviceName);
                profit.setSum(sum);
                
                profits.add(profit);
            }
            beans.put("caption", super.tableModel.getCaption());
            beans.put("profits", profits);
        } catch (ObjectLookupException ex) {
            LOGGER.error(null, ex);
        } catch (DecoratorException ex) {
            LOGGER.error(null, ex);
        }
        return beans;
    }

}
