package com.netcracker.wind.dao.implementations.oracle.reports;

import com.netcracker.wind.dao.implementations.helper.AbstractOracleDAO;
import com.netcracker.wind.dao.interfaces.reports.ICiaIptDAO;
import com.netcracker.wind.entities.reports.CiaIpt;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

/**
 *
 * @author Alexander Kovriga
 */
public class OracleCiaIptDAO extends AbstractOracleDAO implements ICiaIptDAO {

    public static final String DEVICE_ID = "device_id";
    public static final String DEVICE_NAME = "device_name";
    public static final String PORT_ID = "port_id";
    public static final String PORT_NAME = "port_name";
    public static final String PORT_STATUS = "port_status";
    public static final String CIRCUIT_ID = "circuit_id";
    public static final String CIRCUIT_NAME = "circuit_name";
    public static final String SERVICE_INSTANCE_ID = "service_instance_id";
    public static final String SERVICE_INSTANCE_NAME = "service_instance_name";
    public static final String QUERY
            = "SELECT d.id AS " + DEVICE_ID + ", d.name AS " + DEVICE_NAME
            + ", p.id AS " + PORT_ID + ", p.name AS " + PORT_NAME + ", "
            + "(CASE WHEN p.free = 1 THEN 'FREE' ELSE 'BUSY' END) AS "
            + PORT_STATUS + ", c.id AS " + CIRCUIT_ID + ", c.name AS "
            + CIRCUIT_NAME + ", si.id AS " + SERVICE_INSTANCE_ID + ", "
            + "si.name AS " + SERVICE_INSTANCE_NAME + " "
            + "FROM devices d LEFT JOIN ports p ON d.id = p.device_id "
            + "LEFT JOIN circuits c ON p.id = c.PORT_ID "
            + "LEFT JOIN service_instances si ON si.id = c.service_instance_id "
            + "ORDER BY d.id";

    private static final Logger LOGGER
            = Logger.getLogger(OracleCiaIptDAO.class.getName());

    @Override
    protected List parseResult(ResultSet rs) {
        List<CiaIpt> links = new ArrayList<CiaIpt>();
        try {
            while (rs.next()) {
                int deviceId = rs.getInt(DEVICE_ID);
                String deviceName = rs.getString(DEVICE_NAME);
                int portId = rs.getInt(PORT_ID);
                String portName = rs.getString(PORT_NAME);
                String portStatus = rs.getString(PORT_STATUS);
                int circuitId = rs.getInt(CIRCUIT_ID);
                String circuitName = rs.getString(CIRCUIT_NAME);
                int serviceInstanceId = rs.getInt(SERVICE_INSTANCE_ID);
                String serviceInstanceName
                        = rs.getString(SERVICE_INSTANCE_NAME);
                CiaIpt link = new CiaIpt();
                link.setDeviceId(deviceId);
                link.setDeviceName(deviceName);
                link.setPortId(portId);
                link.setPortName(portName);
                link.setPortStatus(portStatus);
                link.setCircuitId(circuitId);
                link.setCircuitName(circuitName);
                link.setServiceInstanceId(serviceInstanceId);
                link.setServiceInstanceName(serviceInstanceName);
                links.add(link);
            }
        } catch (SQLException ex) {
            LOGGER.error(null, ex);
        }
        return links;
    }

    @Override
    public void delete(String deleteQuery, int id) {
    }

    public List<CiaIpt> findAll() {
        return super.findWhere(QUERY, new Object[]{}, DEFAULT_PAGE_NUMBER,
                ALL_RECORDS);
    }

}
