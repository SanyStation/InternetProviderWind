package com.netcracker.wind.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 *
 * @author Anatolii
 */
public class ServiceOrder implements Serializable {

    private static final long serialVersionUID = 2972707714220134975L;

    private Integer id;
    private Date enterdate;
    private Date procesdate;
    private Date completedate;
    private String status;
    private String scenario;
    //one order - one istance ???
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

        HashCodeBuilder builder = new HashCodeBuilder();
        builder.append(id);
        builder.append(enterdate);
        builder.append(procesdate);
        builder.append(completedate);
        builder.append(status);
        builder.append(scenario);
        builder.append(serviceInstancesCollection);
        builder.append(tasksCollection);
        builder.append(users);
        builder.append(serviceLocations);
        builder.append(services);
        builder.append(providerLocations);

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
        if (!(object instanceof ServiceOrder)) {
            return false;
        }

        ServiceOrder rhs = (ServiceOrder) object;
        EqualsBuilder builder = new EqualsBuilder();
        builder.append(id, rhs.getId());
        builder.append(enterdate, rhs.getEnterdate());
        builder.append(procesdate, rhs.getProcesdate());
        builder.append(completedate, rhs.getCompletedate());
        builder.append(status, rhs.getStatus());
        builder.append(scenario, rhs.getScenario());
        builder.append(serviceInstancesCollection, rhs.getServiceInstancesCollection());
        builder.append(tasksCollection, rhs.getTasksCollection());
        builder.append(users, rhs.getUsers());
        builder.append(serviceLocations, rhs.getServiceLocations());
        builder.append(services, rhs.getServices());
        builder.append(providerLocations, rhs.getProviderLocations());

        return builder.isEquals();
    }

    @Override
    public String toString() {
        return "com.netcracker.wind.entities.ServiceOrders[ id=" + id + " ]";
    }

}
