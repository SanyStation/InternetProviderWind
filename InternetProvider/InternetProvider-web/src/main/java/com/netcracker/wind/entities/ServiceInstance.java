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
public class ServiceInstance implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer id;
    private String status;
    private User users;
    private ServiceOrder serviceOrders;
    private Service services;
    private Collection<Circuit> circuitsCollection;
    private Collection<Cable> cablesCollection;

    public ServiceInstance() {
    }

    public ServiceInstance(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public User getUsers() {
        return users;
    }

    public void setUsers(User users) {
        this.users = users;
    }

    public ServiceOrder getServiceOrders() {
        return serviceOrders;
    }

    public void setServiceOrders(ServiceOrder serviceOrders) {
        this.serviceOrders = serviceOrders;
    }

    public Service getServices() {
        return services;
    }

    public void setServices(Service services) {
        this.services = services;
    }

    public Collection<Circuit> getCircuitsCollection() {
        return circuitsCollection;
    }

    public void setCircuitsCollection(Collection<Circuit> circuitsCollection) {
        this.circuitsCollection = circuitsCollection;
    }

    public Collection<Cable> getCablesCollection() {
        return cablesCollection;
    }

    public void setCablesCollection(Collection<Cable> cablesCollection) {
        this.cablesCollection = cablesCollection;
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
        if (!(object instanceof ServiceInstance)) {
            return false;
        }
        ServiceInstance other = (ServiceInstance) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.netcracker.wind.entities.ServiceInstances[ id=" + id + " ]";
    }

}
