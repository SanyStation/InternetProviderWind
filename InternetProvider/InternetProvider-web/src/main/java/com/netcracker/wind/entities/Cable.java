package com.netcracker.wind.entities;

import com.netcracker.wind.dao.factory.AbstractFactoryDAO;
import com.netcracker.wind.dao.factory.FactoryCreator;
import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * Class-entity cable.
 *
 * @author Anatolii
 */
public class Cable implements Serializable {

    private static final long serialVersionUID = -589654569642300050L;

    private final AbstractFactoryDAO factoryDAO
            = FactoryCreator.getInstance().getFactory();

    private Integer id;
    private String name;
    private int serviceLocationId;
    private transient ServiceLocation serviceLocation;
    private int portId;
    private transient Port port;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getServiceLocationId() {
        return serviceLocationId;
    }

    public void setServiceLocationId(int serviceLocationId) {
        this.serviceLocationId = serviceLocationId;
    }

    public ServiceLocation getServiceLocation() {
        if (serviceLocation == null) {
            serviceLocation = factoryDAO.createServiceLocationDAO()
                    .findById(serviceLocationId);
        }
        return serviceLocation;
    }

    public void setServiceLocation(ServiceLocation serviceLocation) {
        this.serviceLocation = serviceLocation;
    }

    public int getPortId() {
        return portId;
    }

    public void setPortId(int portId) {
        this.portId = portId;
    }

    public Port getPort() {
        if (port == null) {
            port = factoryDAO.createPortDAO().findById(portId);
        }
        return port;
    }

    public void setPort(Port ports) {
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
        builder.append(port, rhs.getPort());
        return builder.isEquals();
    }

    @Override
    public String toString() {
        return "com.netcracker.wind.entities.Cables[ id=" + id + " ]";
    }

}
