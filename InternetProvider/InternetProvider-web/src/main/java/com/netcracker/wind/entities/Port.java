package com.netcracker.wind.entities;

import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 *
 * @author Anatolii
 */
public class Port implements Serializable {

    private static final long serialVersionUID = 1983449719630976337L;

    private Integer id;
    private boolean free;
    private Device device;
    private Circuit circuit;
    private Cable cable;

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

    public boolean isFree() {
        return free;
    }

    public void setFree(boolean free) {
        this.free = free;
    }

    public Device getDevice() {
        return device;
    }

    public void setDevice(Device devices) {
        this.device = devices;
    }

    public Circuit getCircuit() {
        return circuit;
    }

    public void setCircuit(Circuit circuits) {
        this.circuit = circuits;
    }

    public Cable getCable() {
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
