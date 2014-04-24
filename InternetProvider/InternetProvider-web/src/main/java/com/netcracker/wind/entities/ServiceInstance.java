package com.netcracker.wind.entities;

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

    private Integer id;
    private String status;
    private User user;
    private ServiceOrder serviceOrder;
    private Service service;
    private Circuit circuit;
    private List<ServiceOrder> serviceOrdersList;

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

    public User getUser() {
        return user;
    }

    public void setUser(User users) {
        this.user = users;
    }

    public ServiceOrder getServiceOrder() {
        return serviceOrder;
    }

    public void setServiceOrder(ServiceOrder serviceOrders) {
        this.serviceOrder = serviceOrders;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service services) {
        this.service = services;
    }

    public Circuit getCircuit() {
        return circuit;
    }

    public void setCircuit(Circuit circuitsList) {
        this.circuit = circuitsList;
    }

    public List<ServiceOrder> getServiceOrdersList() {
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
