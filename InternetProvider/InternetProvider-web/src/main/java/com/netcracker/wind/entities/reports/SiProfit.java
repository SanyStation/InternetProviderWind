package com.netcracker.wind.entities.reports;

import java.io.Serializable;

/**
 * The entity that used like data transfer object for si report (profit by
 * certain month).
 * 
 * @author Alexander Kovriga
 */
public class SiProfit implements Serializable {
    
    private static final long serialVersionUID = -2771061543671224890L;
    
    private int providerLocationId;
    private String providerLocationName;
    private int serviceId;
    private String serviceName;
    private double sum;

    public SiProfit() {
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

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }
    
}
