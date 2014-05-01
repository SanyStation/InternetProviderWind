package com.netcracker.wind.entities;

import com.netcracker.wind.dao.factory.AbstractFactoryDAO;
import com.netcracker.wind.dao.factory.implementations.OracleDAOFactory;
import java.io.Serializable;
import java.util.List;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 *
 * @author Anatolii
 */
public class ServiceInstance implements Serializable {

    private static final long serialVersionUID = 9023896353581772798L;
    
    private final AbstractFactoryDAO factoryDAO = new OracleDAOFactory();

    public static enum Status {

        ACTIVE, PLANNED, PRE_DISCONNECTED, DISCONNECTED
    }

    private Integer id;
    private Status status;
    private int userId;
    private transient User user;
    private int serviceOrderId;                  //current serviceOrder id
    private transient ServiceOrder serviceOrder; //current serviceOrder
    private int serviceId;
    private transient Service service;
    private transient Circuit circuit;
    private transient List<ServiceOrder> serviceOrdersList;

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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
    
    public User getUser() {
        if (user == null) {
            user = factoryDAO.createUserDAO().findByID(userId);
        }
        return user;
    }
    
    public void setUser(User users) {
        this.user = users;
    }

    public int getServiceOrderId() {
        return serviceOrderId;
    }

    public void setServiceOrderId(int serviceOrderId) {
        this.serviceOrderId = serviceOrderId;
    }

    public ServiceOrder getServiceOrder() {
        if (serviceOrder == null) {
            serviceOrder = factoryDAO.createServiceOrderDAO()
                    .findByID(serviceOrderId);
        }
        return serviceOrder;
    }

    public void setServiceOrder(ServiceOrder serviceOrders) {
        this.serviceOrder = serviceOrders;
    }

    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

    public Service getService() {
        if (service == null) {
            service = factoryDAO.createServiceDAO().findByID(serviceId);
        }
        return service;
    }

    public void setService(Service services) {
        this.service = services;
    }

    public Circuit getCircuit() {
        if (circuit == null) {
            circuit = factoryDAO.createCircuitDAO().findByServInst(id);
        }
        return circuit;
    }

    public void setCircuit(Circuit circuitsList) {
        this.circuit = circuitsList;
    }

    public List<ServiceOrder> getServiceOrdersList() {
        if (serviceOrdersList == null) {
            serviceOrdersList = factoryDAO.createServiceOrderDAO()
                    .findByServiceInstance(id);
        }
        return serviceOrdersList;
    }

    public void getServiceOrdersList(List<ServiceOrder> serviceOrdersList) {
        this.serviceOrdersList = serviceOrdersList;
    }

    @Override
    public int hashCode() {
        HashCodeBuilder builder = new HashCodeBuilder();
        builder.append(id);
        builder.append(status);
        builder.append(user);
        builder.append(serviceOrder);
        builder.append(service);
        builder.append(circuit);
        builder.append(serviceOrdersList);
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
        builder.append(user, rhs.getUser());
        builder.append(serviceOrder, rhs.getServiceOrder());
        builder.append(service, rhs.getService());
        builder.append(circuit, rhs.getCircuit());
        builder.append(serviceOrdersList, rhs.getServiceOrdersList());
        return builder.isEquals();
    }

    @Override
    public String toString() {
        return "com.netcracker.wind.entities.ServiceInstances[ id=" + id + " ]";
    }

}
