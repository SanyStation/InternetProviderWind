package com.netcracker.wind.entities;

import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 *
 * @author Anatolii
 */
public class Task implements Serializable {

    public enum TaskType {

        NEW_DEVICE, NEW_CABLE, MANAGE_CIRCUIT, DELETE_CIRCUITE, MODIFY_CIRCUIT, SEND_BILL
    }

    public enum TaskStatus {

        NEW, ACTIVE, SUSPENDED, COMPLETED
    }

    private static final long serialVersionUID = -9049678772835215167L;

    private Integer id;
    private String type;
    private String status;
    private User user;
    private ServiceOrder serviceOrder;
    private Role role;

    public Task() {
    }

    public Task(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public Role getRole() {
        return role;
    }

    public void setRole(Role roles) {
        this.role = roles;
    }

    @Override
    public int hashCode() {

        HashCodeBuilder builder = new HashCodeBuilder();
        builder.append(id);
        builder.append(type);
        builder.append(status);
        builder.append(user);
        builder.append(serviceOrder);
        builder.append(role);

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
        if (!(object instanceof Task)) {
            return false;
        }

        Task rhs = (Task) object;
        EqualsBuilder builder = new EqualsBuilder();
        builder.append(id, rhs.getId());
        builder.append(status, rhs.getStatus());
        builder.append(type, rhs.getType());
        builder.append(user, rhs.getUser());
        builder.append(serviceOrder, rhs.getServiceOrder());
        builder.append(role, rhs.getRole());

        return builder.isEquals();
    }

    @Override
    public String toString() {
        return "com.netcracker.wind.entities.Tasks[ id=" + id + " ]";
    }

}
