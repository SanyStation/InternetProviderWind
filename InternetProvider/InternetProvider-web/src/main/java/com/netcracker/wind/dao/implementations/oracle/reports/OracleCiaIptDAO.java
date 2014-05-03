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
    public static final String CIRCUIT_ID = "circuit_id";
    public static final String SERVICE_INSTANCE_ID = "service_instance_id";
    public static final String QUERY
            = "SELECT d.id AS " + DEVICE_ID + ", "
            + "d.name AS " + DEVICE_NAME + ", p.id AS " + PORT_ID + ", "
            + "c.id AS " + CIRCUIT_ID + ", "
            + "si.id AS " + SERVICE_INSTANCE_ID + " "
            + "FROM devices d JOIN ports p ON d.id = p.device_id "
            + "JOIN circuits c ON p.id = c.PORT_ID "
            + "JOIN service_instances si ON si.id = c.service_instance_id "
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
                Integer portId = rs.getInt(PORT_ID);
                Integer circuitId = rs.getInt(CIRCUIT_ID);
                Integer serviceInstanceId = rs.getInt(SERVICE_INSTANCE_ID);
                CiaIpt link = new CiaIpt();
                link.setDeviceId(deviceId);
                link.setDeviceName(deviceName);
                link.setPortId(portId);
                link.setCircuitId(circuitId);
                link.setServiceInstanceId(serviceInstanceId);
                links.add(link);
            }
        } catch (SQLException ex) {
            LOGGER.error(null, ex);
        }
        return links;
    }
    
    @Override
    public void delete(String deleteQuery, int id) {}

    public List<CiaIpt> findAll() {
        return super.findWhere(QUERY, new Object[]{}, DEFAULT_PAGE_NUMBER,
                ALL_RECORDS);
    }

}
