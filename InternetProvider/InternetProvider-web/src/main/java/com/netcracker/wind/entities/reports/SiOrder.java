package com.netcracker.wind.entities.reports;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Alexander Kovriga
 */
public class SiOrder implements Serializable {
    
    private static final long serialVersionUID = -570284085763707851L;
    
    private int id;
    private String name;
    private int providerLocationId;
    private String providerLocationName;
    private int serviceLocationId;
    private String serviceLocationName;
    private int serviceId;
    private String serviceName;
    private Date completeDate;

    public SiOrder() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public Date getCompleteDate() {
        return completeDate;
    }

    public void setCompleteDate(Date completeDate) {
        this.completeDate = completeDate;
    }

    public int getProviderLocationId() {
        return providerLocationId;
    }

    public void setProviderLocationId(int providerLocationId) {
        this.providerLocationId = providerLocationId;
    }

    public String getProviderLocationName() {
        return providerLocationName;
    }

    public void setProviderLocationName(String providerLocationName) {
        this.providerLocationName = providerLocationName;
    }

    public int getServiceLocationId() {
        return serviceLocationId;
    }

    public String getServiceLocationName() {
        return serviceLocationName;
    }

    public void setServiceLocationName(String serviceLocationName) {
        this.serviceLocationName = serviceLocationName;
    }
    
    public void setServiceLocationId(int serviceLocationId) {
        this.serviceLocationId = serviceLocationId;
    }

    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }
    
    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }
    
}
