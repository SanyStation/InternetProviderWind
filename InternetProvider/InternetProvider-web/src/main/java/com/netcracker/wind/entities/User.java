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
public class User implements Serializable {

    private static final long serialVersionUID = 6475947738102035160L;

    private final AbstractFactoryDAO factoryDAO
            = FactoryCreator.getInstance().getFactory();

    private Integer id;
    private String name;
    private String email;
    private String password;
    private boolean blocked;
    private transient List<ServiceInstance> serviceInstancesList;
    private transient List<Task> tasksList;
    private int roleId;
    private transient Role role;
    private transient List<ServiceOrder> serviceOrdersList;

    public User() {
    }

    public User(Integer id) {
        this.id = id;
    }

    public User(Integer id, String password) {
        this.id = id;
        this.password = password;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isBlocked() {
        return blocked;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }

    public String getStatus() {
        return blocked ? "Blocked" : "Unblocked";
    }

    public List<ServiceInstance> getServiceInstancesList() {
        if (serviceInstancesList == null) {
            serviceInstancesList = factoryDAO.createServiceInstanceDAO()
                    .findByUser(id, AbstractOracleDAO.DEFAULT_PAGE_NUMBER,
                            AbstractOracleDAO.ALL_RECORDS);
        }
        return serviceInstancesList;
    }

    public void setServiceInstancesList(List<ServiceInstance> serviceInstancesList) {
        this.serviceInstancesList = serviceInstancesList;
    }

    public List<Task> getTasksList() {
        if (tasksList == null) {
            tasksList = factoryDAO.createTaskDAO().findByUser(id,
                    AbstractOracleDAO.DEFAULT_PAGE_NUMBER,
                    AbstractOracleDAO.ALL_RECORDS);
        }
        return tasksList;
    }

    public void setTasksList(List<Task> tasksList) {
        this.tasksList = tasksList;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public Role getRole() {
        if (role == null) {
            role = factoryDAO.createRoleDAO().findById(roleId);
        }
        return role;
    }

    public void setRole(Role roles) {
        this.role = roles;
    }

    public List<ServiceOrder> getServiceOrdersList() {
        if (serviceOrdersList == null) {
            serviceOrdersList = factoryDAO.createServiceOrderDAO()
                    .findByUser(id, AbstractOracleDAO.DEFAULT_PAGE_NUMBER,
                            AbstractOracleDAO.ALL_RECORDS);
        }
        return serviceOrdersList;
    }

    public void setServiceOrdersList(List<ServiceOrder> serviceOrdersList) {
        this.serviceOrdersList = serviceOrdersList;
    }

    public ServiceInstance getServiceInstance() {
        return getServiceInstancesList().get(0);
    }

    public int getCountServiceInstances() {
        return getServiceInstancesList().size();
    }

    public int getCountServiceOrders() {
        return getServiceOrdersList().size();
    }

    @Override
    public int hashCode() {
        HashCodeBuilder builder = new HashCodeBuilder();
        builder.append(id);
        builder.append(name);
        builder.append(email);
        builder.append(password);
        builder.append(blocked);
        builder.append(serviceOrdersList);
        builder.append(tasksList);
        builder.append(role);
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
        if (!(object instanceof User)) {
            return false;
        }
        User rhs = (User) object;
        EqualsBuilder builder = new EqualsBuilder();
        builder.append(id, rhs.getId());
        builder.append(name, rhs.getName());
        builder.append(email, rhs.getEmail());
        builder.append(password, rhs.getPassword());
        builder.append(blocked, rhs.isBlocked());
        builder.append(serviceInstancesList, rhs.getServiceInstancesList());
        builder.append(tasksList, rhs.getTasksList());
        builder.append(role, rhs.getRole());
        builder.append(serviceOrdersList, rhs.getServiceOrdersList());
        return builder.isEquals();
    }

    @Override
    public String toString() {
        return "com.netcracker.wind.entities.Users[ id=" + id + " ]";
    }

}
