package com.netcracker.wind.entities;

import com.netcracker.wind.dao.factory.impl.OracleDAOFactory;
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
        return portsCollection;
    }

    public void setPortsList(List<Port> portsCollection) {
        this.portsCollection = portsCollection;
    }

    public int getCapacity() {
        return portsCollection.size();
    }

    public int getUtilization() {
        int busyPorts = 0;
        if (portsCollection.isEmpty()) {
            portsCollection = new OracleDAOFactory().createPortDAO().findByDevice(id);
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

        return builder.isEquals();
    }

    @Override
    public String toString() {
        return "com.netcracker.wind.entities.Devices[ id=" + id + " ]";
    }

}
