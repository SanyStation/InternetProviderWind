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
public class ServiceLocation implements Serializable {

    private static final long serialVersionUID = -4939536133882667333L;

    private Integer id;
    private Integer posX;
    private Integer posY;
    private String address;
    private Collection<ServiceOrder> serviceOrdersCollection;

    public ServiceLocation() {
    }

    public ServiceLocation(Integer id) {
        this.id = id;
    }

    public ServiceLocation(Integer id, Integer posX, Integer posY) {
        this.id = id;
        this.posX = posX;
        this.posY = posY;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPosX() {
        return posX;
    }

    public void setPosX(Integer posX) {
        this.posX = posX;
    }

    public Integer getPosY() {
        return posY;
    }

    public void setPosY(Integer posY) {
        this.posY = posY;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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
        builder.append(posX);
        builder.append(posY);
        builder.append(address);
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
        if (!(object instanceof ServiceLocation)) {
            return false;
        }

        ServiceLocation rhs = (ServiceLocation) object;
        EqualsBuilder builder = new EqualsBuilder();
        builder.append(id, rhs.getId());
        builder.append(posX, rhs.getPosX());
        builder.append(posY, rhs.getPosY());
        builder.append(address, rhs.getAddress());
        builder.append(serviceOrdersCollection, rhs.getServiceOrdersCollection());

        return builder.isEquals();
    }

    @Override
    public String toString() {
        return "com.netcracker.wind.entities.ServiceLocations[ id=" + id + " ]";
    }

}
