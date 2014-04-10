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
public class ProviderLocations implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer id;
    private Integer posX;
    private Integer posY;
    private String address;
    private Collection<Prices> pricesCollection;
    private Collection<ServiceOrders> serviceOrdersCollection;

    public ProviderLocations() {
    }

    public ProviderLocations(Integer id) {
        this.id = id;
    }

    public ProviderLocations(Integer id, Integer posX, Integer posY) {
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

    public Collection<Prices> getPricesCollection() {
        return pricesCollection;
    }

    public void setPricesCollection(Collection<Prices> pricesCollection) {
        this.pricesCollection = pricesCollection;
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
        if (!(object instanceof ProviderLocations)) {
            return false;
        }
        ProviderLocations other = (ProviderLocations) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.netcracker.wind.entities.ProviderLocations[ id=" + id + " ]";
    }

}
