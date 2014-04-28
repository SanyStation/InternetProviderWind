package com.netcracker.wind.export.reports;

import com.netcracker.wind.entities.reports.RiRouterUtilNCap;
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
public class RiUtilNCapExcelExport extends AbstractExcelExport {
    
    private static final Logger LOGGER = Logger.getLogger(
                    RiUtilNCapExcelExport.class.getName());
    
    private static final String TEMPLATE_PATH
            = "/reports/templates/template_ri_util_n_cap.xls";

    public RiUtilNCapExcelExport() {
        super(TEMPLATE_PATH);
    }

    @Override
    protected Map fillBeansMap() {
        List<RiRouterUtilNCap> routers = new ArrayList();
        RowIterator rowIterator = super.tableModel.getRowIterator(true);
        while (rowIterator.hasNext()) {
            try {
                Row row = rowIterator.next();
                ColumnIterator columnIterator = row.getColumnIterator(
                                super.tableModel.getHeaderCellList());
                Column column;
                column = columnIterator.nextColumn();
                int id = (Integer) column.getValue(true);
                column = columnIterator.nextColumn();
                String name = (String) column.getValue(true);
                column = columnIterator.nextColumn();
                int capacity = (Integer) column.getValue(true);
                column = columnIterator.nextColumn();
                int utilization = (Integer) column.getValue(true);
                RiRouterUtilNCap r = new RiRouterUtilNCap();
                r.setId(id);
                r.setName(name);
                r.setCapacity(capacity);
                r.setUtilization(utilization);
                routers.add(r);
            } catch (ObjectLookupException ex) {
                LOGGER.error(null, ex);
            } catch (DecoratorException ex) {
                LOGGER.error(null, ex);
            }
        }
        Map beans = new HashMap();
        beans.put("routers", routers);
        return beans;
    }

}
