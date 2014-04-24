package com.netcracker.wind.entities;

import java.io.Serializable;
import java.sql.Date;
import java.util.Collection;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 *
 * @author Anatolii
 */
public class ServiceOrder implements Serializable {

    private static final long serialVersionUID = 2972707714220134975L;

    public static final String ENTERING = "Entering";

    private Integer id;
    private Date enterdate;
    private Date procesdate;
    private Date completedate;
    private String status;
    private String scenario;
    private ServiceInstance serviceInstance;
    private Collection<Task> tasksCollection;
    private User user;
    private ServiceLocation serviceLocation;
    private Service service;
    private ProviderLocation providerLocation;

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

    public ServiceInstance getServiceInstancesCollection() {
        return serviceInstance;
    }

    public void setServiceInstancesCollection(ServiceInstance serviceInstancesCollection) {
        this.serviceInstance = serviceInstancesCollection;
    }

    public Collection<Task> getTasksCollection() {
        return tasksCollection;
    }

    public void setTasksCollection(Collection<Task> tasksCollection) {
        this.tasksCollection = tasksCollection;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User users) {
        this.user = users;
    }

    public ServiceLocation getServiceLocation() {
        return serviceLocation;
    }

    public void setServiceLocation(ServiceLocation serviceLocations) {
        this.serviceLocation = serviceLocations;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service services) {
        this.service = services;
    }

    public ProviderLocation getProviderLocation() {
        return providerLocation;
    }

    public void setProviderLocation(ProviderLocation providerLocations) {
        this.providerLocation = providerLocations;
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
        builder.append(serviceInstance);
        builder.append(tasksCollection);
        builder.append(user);
        builder.append(serviceLocation);
        builder.append(service);
        builder.append(providerLocation);

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
        builder.append(serviceInstance, rhs.getServiceInstancesCollection());
        builder.append(tasksCollection, rhs.getTasksCollection());
        builder.append(user, rhs.getUser());
        builder.append(serviceLocation, rhs.getServiceLocation());
        builder.append(service, rhs.getService());
        builder.append(providerLocation, rhs.getProviderLocation());

        return builder.isEquals();
    }

    @Override
    public String toString() {
        return "com.netcracker.wind.entities.ServiceOrders[ id=" + id + " ]";
    }

}
