/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.wind.entities;

import java.io.Serializable;

/**
 *
 * @author Anatolii
 */
public class Prices implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer id;
    private Integer price;
    private Services services;
    private ProviderLocations providerLocations;

    public Prices() {
    }

    public Prices(Integer id) {
        this.id = id;
    }

    public Prices(Integer id, Integer price) {
        this.id = id;
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Services getServices() {
        return services;
    }

    public void setServices(Services services) {
        this.services = services;
    }

    public ProviderLocations getProviderLocations() {
        return providerLocations;
    }

    public void setProviderLocations(ProviderLocations providerLocations) {
        this.providerLocations = providerLocations;
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
        if (!(object instanceof Prices)) {
            return false;
        }
        Prices other = (Prices) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.netcracker.wind.entities.Prices[ id=" + id + " ]";
    }

}
