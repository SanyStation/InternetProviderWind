/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

    public ServiceInstance getServiceInstances() {
        return serviceInstance;
    }

    public void setServiceInstances(ServiceInstance serviceInstances) {
        this.serviceInstance = serviceInstances;
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
        builder.append(serviceInstance, rhs.getServiceInstances());
        builder.append(port, rhs.getPorts());

        return builder.isEquals();
    }

    @Override
    public String toString() {
        return "com.netcracker.wind.entities.Circuits[ id=" + id + " ]";
    }

}
