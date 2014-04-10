/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.wind.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

/**
 *
 * @author Anatolii
 */
public class ServiceOrder implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer id;
    private Date enterdate;
    private Date procesdate;
    private Date completedate;
    private String status;
    private String scenario;
    private Collection<ServiceInstance> serviceInstancesCollection;
    private Collection<Task> tasksCollection;
    private User users;
    private ServiceLocation serviceLocations;
    private Service services;
    private ProviderLocation providerLocations;

    public ServiceOrder() {
    }

    public ServiceOrder(Integer id) {
        this.id = id;
    }

    public ServiceOrder(Integer id, String scenario) {
        this.id = id;
        this.scenario = scenario;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getEnterdate() {
        return enterdate;
    }

    public void setEnterdate(Date enterdate) {
        this.enterdate = enterdate;
    }

    public Date getProcesdate() {
        return procesdate;
    }

    public void setProcesdate(Date procesdate) {
        this.procesdate = procesdate;
    }

    public Date getCompletedate() {
        return completedate;
    }

    public void setCompletedate(Date completedate) {
        this.completedate = completedate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getScenario() {
        return scenario;
    }

    public void setScenario(String scenario) {
        this.scenario = scenario;
    }

    public Collection<ServiceInstance> getServiceInstancesCollection() {
        return serviceInstancesCollection;
    }

    public void setServiceInstancesCollection(Collection<ServiceInstance> serviceInstancesCollection) {
        this.serviceInstancesCollection = serviceInstancesCollection;
    }

    public Collection<Task> getTasksCollection() {
        return tasksCollection;
    }

    public void setTasksCollection(Collection<Task> tasksCollection) {
        this.tasksCollection = tasksCollection;
    }

    public User getUsers() {
        return users;
    }

    public void setUsers(User users) {
        this.users = users;
    }

    public ServiceLocation getServiceLocations() {
        return serviceLocations;
    }

    public void setServiceLocations(ServiceLocation serviceLocations) {
        this.serviceLocations = serviceLocations;
    }

    public Service getServices() {
        return services;
    }

    public void setServices(Service services) {
        this.services = services;
    }

    public ProviderLocation getProviderLocations() {
        return providerLocations;
    }

    public void setProviderLocations(ProviderLocation providerLocations) {
        this.providerLocations = providerLocations;
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
        if (!(object instanceof ServiceOrder)) {
            return false;
        }
        ServiceOrder other = (ServiceOrder) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.netcracker.wind.entities.ServiceOrders[ id=" + id + " ]";
    }

}
