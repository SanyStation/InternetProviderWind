/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.wind.entities;

import java.io.Serializable;
import java.util.Collection;

/**
 *
 * @author Anatolii
 */
public class Services implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer id;
    private String name;
    private String description;
    private Collection<Prices> pricesCollection;
    private Collection<ServiceInstances> serviceInstancesCollection;
    private Collection<ServiceOrders> serviceOrdersCollection;

    public Services() {
    }

    public Services(Integer id) {
        this.id = id;
    }

    public Services(Integer id, String name) {
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

    public Collection<Prices> getPricesCollection() {
        return pricesCollection;
    }

    public void setPricesCollection(Collection<Prices> pricesCollection) {
        this.pricesCollection = pricesCollection;
    }

    public Collection<ServiceInstances> getServiceInstancesCollection() {
        return serviceInstancesCollection;
    }

    public void setServiceInstancesCollection(Collection<ServiceInstances> serviceInstancesCollection) {
        this.serviceInstancesCollection = serviceInstancesCollection;
    }

    public Collection<ServiceOrders> getServiceOrdersCollection() {
        return serviceOrdersCollection;
    }

    public void setServiceOrdersCollection(Collection<ServiceOrders> serviceOrdersCollection) {
        this.serviceOrdersCollection = serviceOrdersCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Services)) {
            return false;
        }
        Services other = (Services) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.netcracker.wind.entities.Services[ id=" + id + " ]";
    }

}
