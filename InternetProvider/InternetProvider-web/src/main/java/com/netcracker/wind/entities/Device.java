package com.netcracker.wind.entities;

import java.io.Serializable;
import java.util.ArrayList;
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
    private List<Port> ports = new ArrayList();
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
        return ports;
    }

    public void setPortsList(List<Port> portsCollection) {
        this.ports = portsCollection;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        HashCodeBuilder builder = new HashCodeBuilder();
        builder.append(id);
        builder.append(ports);
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
        builder.append(ports, rhs.getPortsList());
        builder.append(name, rhs.getName());

        return builder.isEquals();
    }

    @Override
    public String toString() {
        return "com.netcracker.wind.entities.Devices[ id=" + id + " ]";
    }

}
