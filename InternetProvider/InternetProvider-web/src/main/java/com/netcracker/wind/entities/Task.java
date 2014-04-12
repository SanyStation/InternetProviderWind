/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.wind.entities;

import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 *
 * @author Anatolii
 */
public class Task implements Serializable {

    private static final long serialVersionUID = -9049678772835215167L;

    private Integer id;
    private String type;
    private String status;
    private User users;
    private ServiceOrder serviceOrders;
    private Role roles;

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

    public User getUsers() {
        return users;
    }

    public void setUsers(User users) {
        this.users = users;
    }

    public ServiceOrder getServiceOrders() {
        return serviceOrders;
    }

    public void setServiceOrders(ServiceOrder serviceOrders) {
        this.serviceOrders = serviceOrders;
    }

    public Role getRoles() {
        return roles;
    }

    public void setRoles(Role roles) {
        this.roles = roles;
    }

    @Override
    public int hashCode() {

        HashCodeBuilder builder = new HashCodeBuilder();
        builder.append(id);
        builder.append(type);
        builder.append(status);
        builder.append(users);
        builder.append(serviceOrders);
        builder.append(roles);

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
        builder.append(users, rhs.getUsers());
        builder.append(serviceOrders, rhs.getServiceOrders());
        builder.append(roles, rhs.getRoles());

        return builder.isEquals();
    }

    @Override
    public String toString() {
        return "com.netcracker.wind.entities.Tasks[ id=" + id + " ]";
    }

}
