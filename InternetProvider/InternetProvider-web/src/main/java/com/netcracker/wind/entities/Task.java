package com.netcracker.wind.entities;

import com.netcracker.wind.dao.factory.AbstractFactoryDAO;
import com.netcracker.wind.dao.factory.FactoryCreator;
import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 *
 * @author Anatolii
 */
public class Task implements Serializable {

    private static final long serialVersionUID = -9049678772835215167L;
    
    private final AbstractFactoryDAO factoryDAO
            = FactoryCreator.getInstance().getFactory();
    
    public static enum Type {

        NEW_DEVICE, NEW_CABLE, DELETE_CABLE, MANAGE_CIRCUIT, DELETE_CIRCUIT,
        MODIFY_CIRCUIT, SEND_BILL, CREATE_CIRCUIT, CREATE_CABLE
    }

    public static enum Status {

        NEW, ACTIVE, SUSPENDED, COMPLETED
    }

    private Integer id;
    private Type type;
    private Status status;
    private int userId;
    private transient User user;
    private int serviceOrderId;
    private transient ServiceOrder serviceOrder;
    private int roleId;
    private transient Role role;

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

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
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

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }
    
    public Role getRole() {
        if (role == null) {
            role = factoryDAO.createRoleDAO().findByID(roleId);
        }
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
