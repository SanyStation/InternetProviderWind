package com.netcracker.wind.entities;

import com.netcracker.wind.dao.factory.AbstractFactoryDAO;
import com.netcracker.wind.dao.factory.FactoryCreator;
import java.io.Serializable;
import java.util.List;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 *
 * @author Anatolii
 */
public class ServiceLocation implements Serializable {

    private static final long serialVersionUID = -4939536133882667333L;
    
    private final AbstractFactoryDAO factoryDAO
            = FactoryCreator.getInstance().getFactory();

    private Integer id;
    private double posX;
    private double posY;
    private String address;
    private transient Cable cable;
    private transient List<ServiceOrder> serviceOrdersList;

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

    public double getPosX() {
        return posX;
    }

    public void setPosX(double posX) {
        this.posX = posX;
    }

    public double getPosY() {
        return posY;
    }

    public void setPosY(double posY) {
        this.posY = posY;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    
    public Cable getCable() {
        if (cable == null) {
            cable = factoryDAO.createCableDAO().findByServiceLocation(id);
        }
        return cable;
    }

    public void setCable(Cable cable) {
        this.cable = cable;
    }
    
    public List<ServiceOrder> getServiceOrdersList() {
        if (serviceOrdersList == null) {
            serviceOrdersList = factoryDAO.createServiceOrderDAO()
                    .findByProvLoc(id);
        }
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
        builder.append(cable);
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
        if (!(object instanceof ServiceLocation)) {
            return false;
        }
        ServiceLocation rhs = (ServiceLocation) object;
        EqualsBuilder builder = new EqualsBuilder();
        builder.append(id, rhs.getId());
        builder.append(posX, rhs.getPosX());
        builder.append(posY, rhs.getPosY());
        builder.append(address, rhs.getAddress());
        builder.append(cable, rhs.getCable());
        builder.append(serviceOrdersList, rhs.getServiceOrdersList());
        return builder.isEquals();
    }

    @Override
    public String toString() {
        return "com.netcracker.wind.entities.ServiceLocations[ id=" + id + " ]";
    }

}
