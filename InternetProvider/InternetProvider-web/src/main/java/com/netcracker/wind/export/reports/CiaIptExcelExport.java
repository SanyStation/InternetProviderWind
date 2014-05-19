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
 * The {@ CiaIptExcelExport} class designed to exporting cia (impact
 * propagation tree) report to excel file.
 * 
 * @author Alexander Kovriga
 */
public class CiaIptExcelExport extends AbstractExcelExport {

    private static final Logger LOGGER = Logger.getLogger(
            CiaIptExcelExport.class.getName());

    private static final String TEMPLATE_PATH
            = "/reports/templates/template_cia_ipt.xls";

    public CiaIptExcelExport() {
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
                String portName = (String) column.getValue(true);
                column = columnIterator.nextColumn();
                String portStatus = (String) column.getValue(true);
                column = columnIterator.nextColumn();
                String circuitName = (String) column.getValue(true);
                column = columnIterator.nextColumn();
                String serviceInstanceName = (String) column.getValue(true);

                ServiceInstance si = new ServiceInstance();
                si.setName(serviceInstanceName);
                si.setId(DEFAULT_ID);

                Circuit circuit = new Circuit();
                circuit.setName(circuitName);
                circuit.setServiceInstance(si);
                circuit.setId(DEFAULT_ID);

                Port port = new Port();
                port.setName(portName);
                port.setFree(portStatus.equals("FREE"));
                port.setCircuit(circuit);
                port.setId(DEFAULT_ID);

                if (tmpId != deviceId) {
                    device = new Device(deviceId);
                    device.setName(deviceName);
                    device.setPortsList(new ArrayList());
                    devices.add(device);
                    tmpId = deviceId;
                }
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
