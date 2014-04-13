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
public class Role implements Serializable {

    private static final long serialVersionUID = 4491197292843435012L;

    private Integer id;
    private String name;
    private Collection<Task> tasksCollection;
    private Collection<User> usersCollection;

    public Role() {
    }

    public Role(Integer id) {
        this.id = id;
    }

    public Role(Integer id, String name) {
        this.id = id;
        this.name = name;
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

    public Collection<Task> getTasksCollection() {
        return tasksCollection;
    }

    public void setTasksCollection(Collection<Task> tasksCollection) {
        this.tasksCollection = tasksCollection;
    }

    public Collection<User> getUsersCollection() {
        return usersCollection;
    }

    public void setUsersCollection(Collection<User> usersCollection) {
        this.usersCollection = usersCollection;
    }

    @Override
    public int hashCode() {
        HashCodeBuilder builder = new HashCodeBuilder();
        builder.append(id);
        builder.append(name);
        builder.append(tasksCollection);
        builder.append(usersCollection);

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
        if (!(object instanceof Role)) {
            return false;
        }

        Role rhs = (Role) object;
        EqualsBuilder builder = new EqualsBuilder();
        builder.append(id, rhs.getId());
        builder.append(name, rhs.getName());
        builder.append(tasksCollection, rhs.getTasksCollection());
        builder.append(usersCollection, rhs.getUsersCollection());

        return builder.isEquals();
    }

    @Override
    public String toString() {
        return "com.netcracker.wind.entities.Roles[ id=" + id + " ]";
    }

}
