package com.netcracker.wind.export.reports;

import com.netcracker.wind.entities.Circuit;
import com.netcracker.wind.entities.Device;
import com.netcracker.wind.entities.Port;
import com.netcracker.wind.entities.ServiceInstance;
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
public class CiaIptExport extends AbstractExcelExport {

    private static final Logger LOGGER = Logger.getLogger(
            CiaIptExport.class.getName());

    private static final String TEMPLATE_PATH
            = "/reports/templates/template_cia_ipt.xls";

    public CiaIptExport() {
        super(TEMPLATE_PATH);
    }

    @Override
    protected Map fillBeansMap() {
        List<Device> devices = new ArrayList<Device>();
        Map beans = new HashMap();
        RowIterator rowIterator = super.tableModel.getRowIterator(true);
        try {
            int tmpId = -1;
            Device device = null;
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                ColumnIterator columnIterator = row.getColumnIterator(
                        super.tableModel.getHeaderCellList());
                
                Column column;
                column = columnIterator.nextColumn();
                int deviceId = (Integer) column.getValue(true);
                column = columnIterator.nextColumn();
                String deviceName = (String) column.getValue(true);
                column = columnIterator.nextColumn();
                int portId = (Integer) column.getValue(true);
                column = columnIterator.nextColumn();
                int circuitId = (Integer) column.getValue(true);
                column = columnIterator.nextColumn();
                int serviceInstanceId = (Integer) column.getValue(true);

                ServiceInstance si = new ServiceInstance();
                si.setId(serviceInstanceId);

                Circuit circuit = new Circuit();
                circuit.setId(circuitId);
                circuit.setServiceInstance(si);

                Port port = new Port();
                port.setId(portId);
                port.setCircuit(circuit);
                
                if (tmpId != deviceId) {
                    device = new Device();
                    devices.add(device);
                    tmpId = deviceId;
                }
                device.setId(deviceId);
                device.setName(deviceName);
                device.getPortsList().add(port);
            }
        } catch (ObjectLookupException ex) {
            LOGGER.error(null, ex);
        } catch (DecoratorException ex) {
            LOGGER.error(null, ex);
        }
        beans.put("devices", devices);
        return beans;
    }

}
