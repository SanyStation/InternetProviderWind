package com.netcracker.wind.entities;

import com.netcracker.wind.dao.factory.AbstractFactoryDAO;
import com.netcracker.wind.dao.factory.FactoryCreator;
import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 *
 * @author Anatolii
 */
public class Port implements Serializable {

    private static final long serialVersionUID = 1983449719630976337L;
    
    private final AbstractFactoryDAO factoryDAO
            = FactoryCreator.getInstance().getFactory();

    private Integer id;
    private String name;
    private boolean free;
    private int deviceId;
    private transient Device device;
    private transient Circuit circuit;
    private transient Cable cable;

    public Port() {
    }

    public Port(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public boolean isFree() {
        return free;
    }

    public void setFree(boolean free) {
        this.free = free;
    }

    public int getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(int deviceId) {
        this.deviceId = deviceId;
    }

    public Device getDevice() {
        if (device == null) {
            device = factoryDAO.createDeviceDAO().findByID(deviceId);
        }
        return device;
    }

    public void setDevice(Device devices) {
        this.device = devices;
    }

    public Circuit getCircuit() {
        if (circuit == null) {
            circuit = factoryDAO.createCircuitDAO().findByPort(id);
        }
        return circuit;
    }

    public void setCircuit(Circuit circuits) {
        this.circuit = circuits;
    }
    
    public Cable getCable() {
        if (cable == null) {
            cable = factoryDAO.createCableDAO().findByPort(id);
        }
        return cable;
    }

    public void setCable(Cable cable) {
        this.cable = cable;
    }

    @Override
    public int hashCode() {
        HashCodeBuilder builder = new HashCodeBuilder();
        builder.append(id);
        builder.append(free);
        builder.append(circuit);
        builder.append(cable);
        return builder.toHashCode();
    }

    @Override
    public boolean equals(Object object) {
        if (object == null) {
            return false;
        }
        if (object == this) {
            return true;
        }
        if (!(object instanceof Port)) {
            return false;
        }
        Port rhs = (Port) object;
        EqualsBuilder builder = new EqualsBuilder();
        builder.append(id, rhs.getId());
        builder.append(free, rhs.isFree());
        builder.append(device, rhs.getDevice());
        builder.append(circuit, rhs.getCircuit());
        builder.append(cable, rhs.getCable());
        return builder.isEquals();
    }

    @Override
    public String toString() {
        return "com.netcracker.wind.entities.Ports[ id=" + id + " ]";
    }

}
