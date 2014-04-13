/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.wind.entities;

import java.io.Serializable;
import java.util.Collection;
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
    private Collection<ServiceInstance> serviceInstancesCollection;
    private Collection<Task> tasksCollection;
    private Role roles;
    private Collection<ServiceOrder> serviceOrdersCollection;

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

    public Role getRoles() {
        return roles;
    }

    public void setRoles(Role roles) {
        this.roles = roles;
    }

    public Collection<ServiceOrder> getServiceOrdersCollection() {
        return serviceOrdersCollection;
    }

    public void setServiceOrdersCollection(Collection<ServiceOrder> serviceOrdersCollection) {
        this.serviceOrdersCollection = serviceOrdersCollection;
    }

    @Override
    public int hashCode() {

        HashCodeBuilder builder = new HashCodeBuilder();
        builder.append(id);
        builder.append(name);
        builder.append(email);
        builder.append(password);
        builder.append(blocked);
        builder.append(serviceOrdersCollection);
        builder.append(tasksCollection);
        builder.append(roles);
        builder.append(serviceOrdersCollection);

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
        builder.append(serviceInstancesCollection, rhs.getServiceInstancesCollection());
        builder.append(tasksCollection, rhs.getTasksCollection());
        builder.append(roles, rhs.getRoles());
        builder.append(serviceOrdersCollection, rhs.getServiceOrdersCollection());

        return builder.isEquals();
    }

    @Override
    public String toString() {
        return "com.netcracker.wind.entities.Users[ id=" + id + " ]";
    }

}
