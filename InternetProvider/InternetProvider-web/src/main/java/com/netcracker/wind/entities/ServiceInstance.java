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
public class ServiceInstance implements Serializable {

    private static final long serialVersionUID = 9023896353581772798L;

    private Integer id;
    private String status;
    private User users;
    private ServiceOrder serviceOrders;
    private Service services;
    //one circuit - one instance ???
    private Collection<Circuit> circuitsCollection;
    //one cable - one instanse ???
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

        HashCodeBuilder builder = new HashCodeBuilder();
        builder.append(id);
        builder.append(status);
        builder.append(users);
        builder.append(serviceOrders);
        builder.append(services);
        builder.append(circuitsCollection);
        builder.append(cablesCollection);

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
        if (!(object instanceof ServiceInstance)) {
            return false;
        }

        ServiceInstance rhs = (ServiceInstance) object;
        EqualsBuilder builder = new EqualsBuilder();
        builder.append(id, rhs.getId());
        builder.append(status, rhs.getStatus());
        builder.append(users, rhs.getUsers());
        builder.append(serviceOrders, rhs.getServiceOrders());
        builder.append(services, rhs.getServices());
        builder.append(circuitsCollection, rhs.getCircuitsCollection());
        builder.append(cablesCollection, rhs.getCablesCollection());

        return builder.isEquals();
    }

    @Override
    public String toString() {
        return "com.netcracker.wind.entities.ServiceInstances[ id=" + id + " ]";
    }

}
