package com.netcracker.wind.entities;

import com.netcracker.wind.dao.factory.AbstractFactoryDAO;
import com.netcracker.wind.dao.factory.implementations.OracleDAOFactory;
import java.io.Serializable;
import java.util.List;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 *
 * @author Anatolii
 */
public class Service implements Serializable {

    private static final long serialVersionUID = 810980848160712242L;
    
    private final AbstractFactoryDAO factoryDAO = new OracleDAOFactory();

    private Integer id;
    private String name;
    private String description;
    private transient List<Price> pricesList;
    private transient List<ServiceInstance> serviceInstancesList;
    private transient List<ServiceOrder> serviceOrdersList;

    public Service() {
    }

    public Service(Integer id) {
        this.id = id;
    }

    public Service(Integer id, String name) {
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    public List<Price> getPricesList() {
        if (pricesList == null) {
            pricesList = factoryDAO.createPriceDAO().findByService(id);
        }
        return pricesList;
    }

    public void setPricesList(List<Price> pricesList) {
        this.pricesList = pricesList;
    }
    
    public List<ServiceInstance> getServiceInstancesList() {
        if (serviceInstancesList == null) {
            serviceInstancesList = factoryDAO.createServiceInstanceDAO()
                    .findByService(id);
        }
        return serviceInstancesList;
    }

    public void setServiceInstancesList(List<ServiceInstance> serviceInstancesList) {
        this.serviceInstancesList = serviceInstancesList;
    }
    
    public List<ServiceOrder> getServiceOrdersList() {
        if (serviceOrdersList == null) {
            serviceOrdersList = factoryDAO.createServiceOrderDAO()
                    .findByService(id);
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
        builder.append(name);
        builder.append(description);
        builder.append(pricesList);
        builder.append(serviceInstancesList);
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
        if (!(object instanceof Service)) {
            return false;
        }
        Service rhs = (Service) object;
        EqualsBuilder builder = new EqualsBuilder();
        builder.append(id, rhs.getId());
        builder.append(name, rhs.getName());
        builder.append(description, rhs.getDescription());
        builder.append(pricesList, rhs.getPricesList());
        builder.append(serviceInstancesList, rhs.getServiceInstancesList());
        builder.append(serviceOrdersList, rhs.getServiceOrdersList());
        return builder.isEquals();
    }

    @Override
    public String toString() {
        return "com.netcracker.wind.entities.Services[ id=" + id + " ]";
    }

}
