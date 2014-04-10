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
public class ServiceInstances implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer id;
    private String status;
    private Users users;
    private ServiceOrders serviceOrders;
    private Services services;
    private Collection<Circuits> circuitsCollection;
    private Collection<Cables> cablesCollection;

    public ServiceInstances() {
    }

    public ServiceInstances(Integer id) {
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

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public ServiceOrders getServiceOrders() {
        return serviceOrders;
    }

    public void setServiceOrders(ServiceOrders serviceOrders) {
        this.serviceOrders = serviceOrders;
    }

    public Services getServices() {
        return services;
    }

    public void setServices(Services services) {
        this.services = services;
    }

    public Collection<Circuits> getCircuitsCollection() {
        return circuitsCollection;
    }

    public void setCircuitsCollection(Collection<Circuits> circuitsCollection) {
        this.circuitsCollection = circuitsCollection;
    }

    public Collection<Cables> getCablesCollection() {
        return cablesCollection;
    }

    public void setCablesCollection(Collection<Cables> cablesCollection) {
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
        if (!(object instanceof ServiceInstances)) {
            return false;
        }
        ServiceInstances other = (ServiceInstances) object;
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
