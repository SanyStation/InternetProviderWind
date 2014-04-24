package com.netcracker.wind.entities;

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

    private Integer id;
    private String name;
    private String email;
    private String password;
    private boolean blocked;
    private List<ServiceInstance> serviceInstancesList;
    private List<Task> tasksList;
    private Role role;
    private List<ServiceOrder> serviceOrdersList;

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

    public boolean getBlocked() {
        return blocked;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }

    public List<ServiceInstance> getServiceInstancesList() {
        return serviceInstancesList;
    }

    public void setServiceInstancesList(List<ServiceInstance> serviceInstancesList) {
        this.serviceInstancesList = serviceInstancesList;
    }

    public List<Task> getTasksList() {
        return tasksList;
    }

    public void setTasksList(List<Task> tasksList) {
        this.tasksList = tasksList;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role roles) {
        this.role = roles;
    }

    public List<ServiceOrder> getServiceOrdersList() {
        return serviceOrdersList;
    }

    public void setServiceOrdersList(List<ServiceOrder> serviceOrdersList) {
        this.serviceOrdersList = serviceOrdersList;
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
        builder.append(blocked, rhs.getBlocked());
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
