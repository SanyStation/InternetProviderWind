package com.netcracker.wind.entities;

import com.netcracker.wind.dao.factory.implementations.OracleDAOFactory;
import java.io.Serializable;
import java.util.List;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 *
 * @author Anatolii
 */
public class Device implements Serializable {

    private static final long serialVersionUID = -2152609240262921250L;

    private Integer id;
    private List<Port> portsCollection;
    private String name;

    public Device() {
    }

    public Device(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Port> getPortsList() {
        if (portsCollection == null) {
            fillPortsCollection();
        }
        return portsCollection;
    }

    public void setPortsList(List<Port> portsCollection) {
        this.portsCollection = portsCollection;
    }

    private void fillPortsCollection() {
        portsCollection
                = new OracleDAOFactory().createPortDAO().findByDevice(id);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCapacity() {
        if (portsCollection == null) {
            fillPortsCollection();
        }
        return portsCollection.size();
    }

    public int getUtilization() {
        int busyPorts = 0;
        if (portsCollection == null) {
            fillPortsCollection();
        }
        for (Port port : portsCollection) {
            if (!port.isFree()) {
                ++busyPorts;
            }
        }
        return busyPorts;
    }

    public double getUtilizationPercent() {
        return getCapacity() > 0
                ? (double) getUtilization() / getCapacity() : 0;
    }

    @Override
    public int hashCode() {
        HashCodeBuilder builder = new HashCodeBuilder();
        builder.append(id);
        builder.append(portsCollection);
        builder.append(name);

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
        if (!(object instanceof Device)) {
            return false;
        }
        Device rhs = (Device) object;
        EqualsBuilder builder = new EqualsBuilder();
        builder.append(id, rhs.getId());
        builder.append(portsCollection, rhs.getPortsList());
        builder.append(name, rhs.getName());

        return builder.isEquals();
    }

    @Override
    public String toString() {
        return "com.netcracker.wind.entities.Devices[ id=" + id + " ]";
    }

}
