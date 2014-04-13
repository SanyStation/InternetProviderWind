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
public class Price implements Serializable {

    private static final long serialVersionUID = 3404424945291368070L;

    private Integer id;
    private Integer price;
    private Service services;
    private ProviderLocation providerLocations;

    public Price() {
    }

    public Price(Integer id) {
        this.id = id;
    }

    public Price(Integer id, Integer price) {
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

    public Service getServices() {
        return services;
    }

    public void setServices(Service services) {
        this.services = services;
    }

    public ProviderLocation getProviderLocations() {
        return providerLocations;
    }

    public void setProviderLocations(ProviderLocation providerLocations) {
        this.providerLocations = providerLocations;
    }

    @Override
    public int hashCode() {

        HashCodeBuilder builder = new HashCodeBuilder();
        builder.append(id);
        builder.append(price);
        builder.append(services);
        builder.append(providerLocations);

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
        if (!(object instanceof Price)) {
            return false;
        }

        Price rhs = (Price) object;
        EqualsBuilder builder = new EqualsBuilder();
        builder.append(id, rhs.getId());
        builder.append(price, rhs.getPrice());
        builder.append(services, rhs.getServices());
        builder.append(providerLocations, rhs.getProviderLocations());

        return builder.isEquals();
    }

    @Override
    public String toString() {
        return "com.netcracker.wind.entities.Prices[ id=" + id + " ]";
    }

}
