package com.netcracker.wind.entities;

import com.netcracker.wind.dao.factory.AbstractFactoryDAO;
import com.netcracker.wind.dao.factory.FactoryCreator;
import com.netcracker.wind.dao.implementations.helper.AbstractOracleDAO;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 *
 * @author Anatolii
 */
public class ServiceOrder implements Serializable {

    private static final long serialVersionUID = 2972707714220134975L;

    private final AbstractFactoryDAO factoryDAO
            = FactoryCreator.getInstance().getFactory();

    public static enum Status {

        ENTERING, PROCESSING, CANCELLED, COMPLETED

    }

    public static enum Scenario {

        NEW, MODIFY, DISCONNECT

    }

    private Integer id;
    private Timestamp enterdate;
    private Timestamp procesdate;
    private Timestamp completedate;
    private Status status;
    private Scenario scenario;
    private int serviceInstanceId;
    private transient ServiceInstance serviceInstance;
    private transient List<Task> tasksList;
    private int userId;
    private transient User user;
    private int serviceLocationId;
    private transient ServiceLocation serviceLocation;
    private int serviceId;
    private transient Service service;
    private int providerLocationId;
    private transient ProviderLocation providerLocation;
    private transient Price price;

    public ServiceOrder() {
    }

    public ServiceOrder(Integer id) {
        this.id = id;
    }

    public ServiceOrder(Integer id, Scenario scenario) {
        this.id = id;
        this.scenario = scenario;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Timestamp getEnterdate() {
        return enterdate;
    }

    public void setEnterdate(Timestamp enterdate) {
        this.enterdate = enterdate;
    }

    public Timestamp getProcesdate() {
        return procesdate;
    }

    public void setProcesdate(Timestamp procesdate) {
        this.procesdate = procesdate;
    }

    public Timestamp getCompletedate() {
        return completedate;
    }

    public void setCompletedate(Timestamp completedate) {
        this.completedate = completedate;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Scenario getScenario() {
        return scenario;
    }

    public void setScenario(Scenario scenario) {
        this.scenario = scenario;
    }

    public int getServiceInstanceId() {
        return serviceInstanceId;
    }

    public void setServiceInstanceId(int serviceInstanceId) {
        this.serviceInstanceId = serviceInstanceId;
    }

    public ServiceInstance getServiceInstance() {
        if (serviceInstance == null) {
            serviceInstance = factoryDAO.createServiceInstanceDAO().
                    findById(serviceInstanceId);
        }
        return serviceInstance;
    }

    public void setServiceInstance(ServiceInstance serviceInstance) {
        this.serviceInstance = serviceInstance;
        this.serviceInstanceId = serviceInstance.getId();
    }

    public List<Task> getTasksList() {
        if (tasksList == null) {
            tasksList = factoryDAO.createTaskDAO().findByServiceOrder(id,
                    AbstractOracleDAO.DEFAULT_PAGE_NUMBER,
                    AbstractOracleDAO.ALL_RECORDS);
        }
        return tasksList;
    }

    public void setTasksCollection(List<Task> tasksList) {
        this.tasksList = tasksList;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public User getUser() {
        if (user == null) {
            user = factoryDAO.createUserDAO().findById(userId);
        }
        return user;
    }

    public void setUser(User users) {
        this.user = users;
    }

    public int getServiceLocationId() {
        return serviceLocationId;
    }

    public void setServiceLocationId(int serviceLocationId) {
        this.serviceLocationId = serviceLocationId;
    }

    public ServiceLocation getServiceLocation() {
        if (serviceLocation == null) {
            serviceLocation = factoryDAO.createServiceLocationDAO()
                    .findById(serviceLocationId);
        }
        return serviceLocation;
    }

    public void setServiceLocation(ServiceLocation serviceLocations) {
        this.serviceLocation = serviceLocations;
    }

    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

    public Service getService() {
        if (service == null) {
            service = factoryDAO.createServiceDAO().findById(serviceId);
        }
        return service;
    }

    public void setService(Service services) {
        this.service = services;
    }

    public int getProviderLocationId() {
        return providerLocationId;
    }

    public void setProviderLocationId(int providerLocationId) {
        this.providerLocationId = providerLocationId;
    }

    public ProviderLocation getProviderLocation() {
        if (providerLocation == null) {
            providerLocation = factoryDAO.createProviderLocationDAO()
                    .findById(providerLocationId);
        }
        return providerLocation;
    }

    public void setProviderLocation(ProviderLocation providerLocations) {
        this.providerLocation = providerLocations;
        this.providerLocationId = providerLocation.getId();
    }
    public Price getPrice() {
        if (price == null) {
            List<Price> prices = getProviderLocation().getPricesList();
            for (Price price1 : prices) {
                if (price1.getServiceId() == this.getServiceId()){
                    price = price1;
                    break;
                }
            }
        }
        return price;
    }

    public void setPrice(Price price) {
        this.price = price;
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
        builder.append(tasksList);
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
        builder.append(serviceInstance, rhs.getServiceInstance());
        builder.append(tasksList, rhs.getTasksList());
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
