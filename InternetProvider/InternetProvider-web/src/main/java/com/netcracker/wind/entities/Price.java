package com.netcracker.wind.entities;

import com.netcracker.wind.dao.factory.AbstractFactoryDAO;
import com.netcracker.wind.dao.factory.FactoryCreator;
import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 *
 * @author Anatolii
 */
public class Price implements Serializable {

    private static final long serialVersionUID = 3404424945291368070L;
    
    private final AbstractFactoryDAO factoryDAO
            = FactoryCreator.getInstance().getFactory();

    private Integer id;
    private Integer price;
    private int serviceId;
    private transient Service service;
    private int providerLocationId;
    private transient ProviderLocation providerLocation;

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

    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }
    
    public Service getService() {
        if (service == null) {
            service = factoryDAO.createServiceDAO().findById(serviceId);
        }
        return service;
    }

    public void setService(Service services) {
        this.service = services;
    }

    public int getProviderLocationId() {
        if (providerLocation == null) {
            providerLocation = factoryDAO.createProviderLocationDAO()
                    .findById(providerLocationId);
        }
        return providerLocationId;
    }

    public void setProviderLocationId(int providerLocationId) {
        this.providerLocationId = providerLocationId;
    }
    
    public ProviderLocation getProviderLocation() {
        return providerLocation;
    }

    public void setProviderLocation(ProviderLocation providerLocations) {
        this.providerLocation = providerLocations;
    }

    @Override
    public int hashCode() {
        HashCodeBuilder builder = new HashCodeBuilder();
        builder.append(id);
        builder.append(price);
        builder.append(service);
        builder.append(providerLocation);
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
        builder.append(service, rhs.getService());
        builder.append(providerLocation, rhs.getProviderLocation());
        return builder.isEquals();
    }

    @Override
    public String toString() {
        return "com.netcracker.wind.entities.Prices[ id=" + id + " ]";
    }

}
