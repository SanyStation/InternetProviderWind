package com.netcracker.wind.entities;

import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 *
 * @author Anatolii
 */
public class Circuit implements Serializable {

    private static final long serialVersionUID = 4851026000306784920L;

    private Integer id;
    private ServiceInstance serviceInstance;
    private Port port;

    public Circuit() {
    }

    public Circuit(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ServiceInstance getServiceInstance() {
        return serviceInstance;
    }

    public void setServiceInstance(ServiceInstance serviceInstances) {
        this.serviceInstance = serviceInstances;
    }

    public Port getPort() {
        return port;
    }

    public void setPort(Port ports) {
        this.port = ports;
    }

    @Override
    public int hashCode() {
        HashCodeBuilder builder = new HashCodeBuilder();
        builder.append(id);
        builder.append(serviceInstance);
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
        if (!(object instanceof Circuit)) {
            return false;
        }
        Circuit rhs = (Circuit) object;
        EqualsBuilder builder = new EqualsBuilder();
        builder.append(id, rhs.getId());
        builder.append(serviceInstance, rhs.getServiceInstance());
        builder.append(port, rhs.getPort());

        return builder.isEquals();
    }

    @Override
    public String toString() {
        return "com.netcracker.wind.entities.Circuits[ id=" + id + " ]";
    }

}
