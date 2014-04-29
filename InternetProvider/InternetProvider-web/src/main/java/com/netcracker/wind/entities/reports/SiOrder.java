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
    private Date completeDate;
    private int providerLocationId;
    private String providerLocationName;
    private int serviceLocationId;
    private String serviceName;

    public SiOrder() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public void setServiceLocationId(int serviceLocationId) {
        this.serviceLocationId = serviceLocationId;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }
    
}
