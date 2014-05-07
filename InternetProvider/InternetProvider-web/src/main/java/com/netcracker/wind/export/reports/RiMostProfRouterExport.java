package com.netcracker.wind.export.reports;

import com.netcracker.wind.entities.reports.RiMostProfRouter;
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
public class RiMostProfRouterExport extends AbstractExcelExport {

    private static final Logger LOGGER = Logger.getLogger(
            RiMostProfRouterExport.class.getName());

    private static final String TEMPLATE_PATH
            = "/reports/templates/template_ri_most_prof.xls";

    public RiMostProfRouterExport() {
        super(TEMPLATE_PATH);
    }

    @Override
    protected Map fillBeansMap() {
        List<RiMostProfRouter> routers = new ArrayList<RiMostProfRouter>();
        Map beans = new HashMap();
        RowIterator rowIterator = super.tableModel.getRowIterator(true);
        try {
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                ColumnIterator columnIterator = row.getColumnIterator(
                        super.tableModel.getHeaderCellList());
                Column column;
                column = columnIterator.nextColumn();
                String name = (String) column.getValue(true);
                column = columnIterator.nextColumn();
                double profit = (Double) column.getValue(true);
                RiMostProfRouter r = new RiMostProfRouter();
                r.setName(name);
                r.setProfit(profit);
                routers.add(r);
            }
        } catch (ObjectLookupException ex) {
            LOGGER.error(null, ex);
        } catch (DecoratorException ex) {
            LOGGER.error(null, ex);
        }
        beans.put("routers", routers);
        return beans;
    }

}
