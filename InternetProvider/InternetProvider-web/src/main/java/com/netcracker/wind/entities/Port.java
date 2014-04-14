/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.wind.entities;

import java.io.Serializable;
import java.util.Collection;
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
    private Device devices;
    private Circuit circuits;
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

    public boolean getFree() {
        return free;
    }

    public void setFree(boolean free) {
        this.free = free;
    }

    public Device getDevices() {
        return devices;
    }

    public void setDevices(Device devices) {
        this.devices = devices;
    }

    public Circuit getCircuits() {
        return circuits;
    }

    public void setCircuits(Circuit circuits) {
        this.circuits = circuits;
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
        builder.append(circuits);
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
        builder.append(free, rhs.getFree());
        builder.append(devices, rhs.getDevices());
        builder.append(circuits, rhs.getCircuits());
        builder.append(cable, rhs.getCable());

        return builder.isEquals();
    }

    @Override
    public String toString() {
        return "com.netcracker.wind.entities.Ports[ id=" + id + " ]";
    }

}
