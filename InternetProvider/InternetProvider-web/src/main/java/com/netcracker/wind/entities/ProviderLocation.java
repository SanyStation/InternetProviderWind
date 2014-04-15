package com.netcracker.wind.entities;

import java.io.Serializable;
import java.util.List;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 *
 * @author Anatolii
 */
public class ProviderLocation implements Serializable {

    private static final long serialVersionUID = -189052472651534830L;

    private Integer id;
    private Integer posX;
    private Integer posY;
    private String address;
    private List<Price> pricesList;
    private List<ServiceOrder> serviceOrdersList;

    public ProviderLocation() {
    }

    public ProviderLocation(Integer id) {
        this.id = id;
    }

    public ProviderLocation(Integer id, Integer posX, Integer posY) {
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

    public List<Price> getPricesList() {
        return pricesList;
    }

    public void setPricesList(List<Price> pricesList) {
        this.pricesList = pricesList;
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
        builder.append(posX);
        builder.append(posY);
        builder.append(address);
        builder.append(pricesList);
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
        if (!(object instanceof ProviderLocation)) {
            return false;
        }

        ProviderLocation rhs = (ProviderLocation) object;
        EqualsBuilder builder = new EqualsBuilder();
        builder.append(id, rhs.getId());
        builder.append(posX, rhs.getPosX());
        builder.append(posY, rhs.getPosY());
        builder.append(address, rhs.getAddress());
        builder.append(pricesList, rhs.getPricesList());
        builder.append(serviceOrdersList, rhs.getServiceOrdersList());

        return builder.isEquals();
    }

    @Override
    public String toString() {
        return "com.netcracker.wind.entities.ProviderLocations[ id=" + id + " ]";
    }

}
