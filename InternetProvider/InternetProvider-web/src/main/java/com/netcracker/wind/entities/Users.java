/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.wind.entities;

import java.io.Serializable;
import java.util.Collection;

/**
 *
 * @author Anatolii
 */
public class Users implements Serializable {

    private static final long serialVersionUID = 1L;
    private Short id;
    private String name;
    private String email;
    private String password;
    private Character blocked;
    private Collection<ServiceInstances> serviceInstancesCollection;
    private Collection<Tasks> tasksCollection;
    private Roles roles;
    private Collection<ServiceOrders> serviceOrdersCollection;

    public Users() {
    }

    public Users(Short id) {
        this.id = id;
    }

    public Users(Short id, String password) {
        this.id = id;
        this.password = password;
    }

    public Short getId() {
        return id;
    }

    public void setId(Short id) {
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

    public Character getBlocked() {
        return blocked;
    }

    public void setBlocked(Character blocked) {
        this.blocked = blocked;
    }

    public Collection<ServiceInstances> getServiceInstancesCollection() {
        return serviceInstancesCollection;
    }

    public void setServiceInstancesCollection(Collection<ServiceInstances> serviceInstancesCollection) {
        this.serviceInstancesCollection = serviceInstancesCollection;
    }

    public Collection<Tasks> getTasksCollection() {
        return tasksCollection;
    }

    public void setTasksCollection(Collection<Tasks> tasksCollection) {
        this.tasksCollection = tasksCollection;
    }

    public Roles getRoles() {
        return roles;
    }

    public void setRoles(Roles roles) {
        this.roles = roles;
    }

    public Collection<ServiceOrders> getServiceOrdersCollection() {
        return serviceOrdersCollection;
    }

    public void setServiceOrdersCollection(Collection<ServiceOrders> serviceOrdersCollection) {
        this.serviceOrdersCollection = serviceOrdersCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Users)) {
            return false;
        }
        Users other = (Users) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.netcracker.wind.entities.Users[ id=" + id + " ]";
    }

}
