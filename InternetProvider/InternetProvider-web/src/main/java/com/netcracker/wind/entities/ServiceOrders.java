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
public class ServiceOrders implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer id;
    private Date enterdate;
    private Date procesdate;
    private Date completedate;
    private String status;
    private String scenario;
    private Collection<ServiceInstances> serviceInstancesCollection;
    private Collection<Tasks> tasksCollection;
    private Users users;
    private ServiceLocations serviceLocations;
    private Services services;
    private ProviderLocations providerLocations;

    public ServiceOrders() {
    }

    public ServiceOrders(Integer id) {
        this.id = id;
    }

    public ServiceOrders(Integer id, String scenario) {
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

    public Collection<ServiceInstances> getServiceInstancesCollection() {
        return serviceInstancesCollection;
    }

    public void setServiceInstancesCollection(Collection<ServiceInstances> serviceInstancesCollection) {
        this.serviceInstancesCollection = serviceInstancesCollection;
    }

    public Collection<Tasks> getTasksCollection() {
        return tasksCollection;
    }

    public void setTasksCollection(Collection<Tasks> tasksCollection) {
        this.tasksCollection = tasksCollection;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public ServiceLocations getServiceLocations() {
        return serviceLocations;
    }

    public void setServiceLocations(ServiceLocations serviceLocations) {
        this.serviceLocations = serviceLocations;
    }

    public Services getServices() {
        return services;
    }

    public void setServices(Services services) {
        this.services = services;
    }

    public ProviderLocations getProviderLocations() {
        return providerLocations;
    }

    public void setProviderLocations(ProviderLocations providerLocations) {
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
        if (!(object instanceof ServiceOrders)) {
            return false;
        }
        ServiceOrders other = (ServiceOrders) object;
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
