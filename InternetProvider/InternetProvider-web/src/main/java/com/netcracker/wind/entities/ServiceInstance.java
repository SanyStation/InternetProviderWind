package com.netcracker.wind.entities;

import com.netcracker.wind.dao.factory.AbstractFactoryDAO;
import com.netcracker.wind.dao.factory.FactoryCreator;
import com.netcracker.wind.dao.implementations.helper.AbstractOracleDAO;
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
    
    private final AbstractFactoryDAO factoryDAO
            = FactoryCreator.getInstance().getFactory();

    public static enum Status {

        ACTIVE, ACTIVE_MODIFY, PLANNED, PRE_DISCONNECTED, DISCONNECTED
    }

    private Integer id;
    private String name;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
            user = factoryDAO.createUserDAO().findById(userId);
        }
        return user;
    }
    
    public void setUser(User user) {
        this.user = user;
        this.userId = user.getId();
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
                    .findById(serviceOrderId);
        }
        return serviceOrder;
    }

    public void setServiceOrder(ServiceOrder serviceOrder) {
        this.serviceOrder = serviceOrder;
        this.serviceOrderId = serviceOrder.getId();
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

    public void setService(Service service) {
        this.service = service;
        this.serviceId = service.getId();
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
            serviceOrdersList = factoryDAO.createServiceOrderDAO().
                    findByServiceInstance(id, 
                    AbstractOracleDAO.DEFAULT_PAGE_NUMBER,
                    AbstractOracleDAO.ALL_RECORDS);
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
