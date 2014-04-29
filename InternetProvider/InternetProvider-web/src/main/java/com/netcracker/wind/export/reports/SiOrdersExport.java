package com.netcracker.wind.export.reports;

import com.netcracker.wind.entities.reports.SiOrder;
import java.util.ArrayList;
import java.util.Date;
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
public class SiOrdersExport extends AbstractExcelExport {

    private static final Logger LOGGER = Logger.getLogger(
            SiOrdersExport.class.getName());

    private static final String TEMPLATE_PATH
            = "/reports/templates/template_si_orders.xls";

    public SiOrdersExport() {
        super(TEMPLATE_PATH);
    }

    @Override
    protected Map fillBeansMap() {
        List<SiOrder> orders = new ArrayList<SiOrder>();
        Map beans = new HashMap();
        RowIterator rowIterator = super.tableModel.getRowIterator(true);
        try {
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                ColumnIterator columnIterator = row.getColumnIterator(
                        super.tableModel.getHeaderCellList());
                Column column;
                column = columnIterator.nextColumn();
                int id = (Integer) column.getValue(true);
                column = columnIterator.nextColumn();
                int providerLocationId = (Integer) column.getValue(true);
                column = columnIterator.nextColumn();
                String providerLocationName = (String) column.getValue(true);
                column = columnIterator.nextColumn();
                int serviceLocationId = (Integer) column.getValue(true);
                column = columnIterator.nextColumn();
                String serviceName = (String) column.getValue(true);
                column = columnIterator.nextColumn();
                Date completeDate = (Date) column.getValue(true);
                
                SiOrder order = new SiOrder();
                order.setId(id);
                order.setProviderLocationId(providerLocationId);
                order.setProviderLocationName(providerLocationName);
                order.setServiceLocationId(serviceLocationId);
                order.setServiceName(serviceName);
                order.setCompleteDate(completeDate);
                
                orders.add(order);
            }
            beans.put("caption", super.tableModel.getCaption());
            beans.put("orders", orders);
        } catch (ObjectLookupException ex) {
            LOGGER.error(null, ex);
        } catch (DecoratorException ex) {
            LOGGER.error(null, ex);
        }
        return beans;
    }

}
