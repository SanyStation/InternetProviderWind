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
 * The {@ SiOrdersExcelExport} class designed to exporting si (defined orders
 * per certain period) report to excel file.
 * 
 * @author Alexander Kovriga
 */
public class SiOrdersExcelExport extends AbstractExcelExport {

    private static final Logger LOGGER = Logger.getLogger(
            SiOrdersExcelExport.class.getName());

    private static final String TEMPLATE_PATH
            = "/reports/templates/template_si_orders.xls";

    public SiOrdersExcelExport() {
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
                String serviceOrderName = (String) column.getValue(true);
                column = columnIterator.nextColumn();
                String providerLocationName = (String) column.getValue(true);
                column = columnIterator.nextColumn();
                String serviceLocationName = (String) column.getValue(true);
                column = columnIterator.nextColumn();
                String serviceName = (String) column.getValue(true);
                column = columnIterator.nextColumn();
                Object obj = column.getValue(true);
                
                SiOrder order = new SiOrder();
                order.setName(serviceOrderName);
                order.setProviderLocationName(providerLocationName);
                order.setServiceLocationName(serviceLocationName);
                order.setServiceName(serviceName);
                if (obj instanceof Date) {
                     order.setCompleteDate((Date) obj);
                }
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
