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
public class Service implements Serializable {

    private static final long serialVersionUID = 810980848160712242L;

    private Integer id;
    private String name;
    private String description;
    private Collection<Price> pricesCollection;
    private Collection<ServiceInstance> serviceInstancesCollection;
    private Collection<ServiceOrder> serviceOrdersCollection;

    public Service() {
    }

    public Service(Integer id) {
        this.id = id;
    }

    public Service(Integer id, String name) {
        this.id = id;
        this.name = name;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Collection<Price> getPricesCollection() {
        return pricesCollection;
    }

    public void setPricesCollection(Collection<Price> pricesCollection) {
        this.pricesCollection = pricesCollection;
    }

    public Collection<ServiceInstance> getServiceInstancesCollection() {
        return serviceInstancesCollection;
    }

    public void setServiceInstancesCollection(Collection<ServiceInstance> serviceInstancesCollection) {
        this.serviceInstancesCollection = serviceInstancesCollection;
    }

    public Collection<ServiceOrder> getServiceOrdersCollection() {
        return serviceOrdersCollection;
    }

    public void setServiceOrdersCollection(Collection<ServiceOrder> serviceOrdersCollection) {
        this.serviceOrdersCollection = serviceOrdersCollection;
    }

    @Override
    public int hashCode() {

        HashCodeBuilder builder = new HashCodeBuilder();
        builder.append(id);
        builder.append(name);
        builder.append(description);
        builder.append(pricesCollection);
        builder.append(serviceInstancesCollection);
        builder.append(serviceOrdersCollection);

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
        if (!(object instanceof Service)) {
            return false;
        }

        Service rhs = (Service) object;
        EqualsBuilder builder = new EqualsBuilder();
        builder.append(id, rhs.getId());
        builder.append(name, rhs.getName());
        builder.append(description, rhs.getDescription());
        builder.append(pricesCollection, rhs.getPricesCollection());
        builder.append(serviceInstancesCollection, rhs.getServiceInstancesCollection());
        builder.append(serviceOrdersCollection, rhs.getServiceOrdersCollection());

        return builder.isEquals();
    }

    @Override
    public String toString() {
        return "com.netcracker.wind.entities.Services[ id=" + id + " ]";
    }

}
