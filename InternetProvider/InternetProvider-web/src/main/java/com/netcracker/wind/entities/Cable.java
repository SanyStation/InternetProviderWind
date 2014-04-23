package com.netcracker.wind.entities;

import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 *
 * @author Anatolii
 */
public class Cable implements Serializable {

    private static final long serialVersionUID = -589654569642300050L;

    private Integer id;
    private ServiceLocation serviceLocation;
    private Port port;

    public Cable() {
    }

    public Cable(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ServiceLocation getServiceLocation() {
        return serviceLocation;
    }

    public void setServiceLocation(ServiceLocation serviceLocation) {
        this.serviceLocation = serviceLocation;
    }

    public Port getPorts() {
        return port;
    }

    public void setPorts(Port ports) {
        this.port = ports;
    }

    @Override
    public int hashCode() {
        HashCodeBuilder builder = new HashCodeBuilder();
        builder.append(id);
        builder.append(serviceLocation);
        builder.append(port);

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
        if (!(object instanceof Cable)) {
            return false;
        }
        Cable rhs = (Cable) object;
        EqualsBuilder builder = new EqualsBuilder();
        builder.append(id, rhs.getId());
        builder.append(serviceLocation, rhs.getServiceLocation());
        builder.append(port, rhs.getPorts());

        return builder.isEquals();
    }

    @Override
    public String toString() {
        return "com.netcracker.wind.entities.Cables[ id=" + id + " ]";
    }

}
