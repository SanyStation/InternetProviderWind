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
public class Device implements Serializable {

    private static final long serialVersionUID = -2152609240262921250L;
    
    private final AbstractFactoryDAO factoryDAO = new OracleDAOFactory();

    private Integer id;
    private transient List<Port> portsList;
    private String name;

    public Device() {
    }

    public Device(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    public List<Port> getPortsList() {
        if (portsList == null) {
            portsList = factoryDAO.createPortDAO().findByDevice(id);
        }
        return portsList;
    }

    public void setPortsList(List<Port> portsList) {
        this.portsList = portsList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        HashCodeBuilder builder = new HashCodeBuilder();
        builder.append(id);
        builder.append(portsList);
        builder.append(name);
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
        if (!(object instanceof Device)) {
            return false;
        }
        Device rhs = (Device) object;
        EqualsBuilder builder = new EqualsBuilder();
        builder.append(id, rhs.getId());
        builder.append(portsList, rhs.getPortsList());
        builder.append(name, rhs.getName());
        return builder.isEquals();
    }

    @Override
    public String toString() {
        return "com.netcracker.wind.entities.Devices[ id=" + id + " ]";
    }

}
