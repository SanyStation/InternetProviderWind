package com.netcracker.wind.entities.reports;

import java.io.Serializable;

/**
 *
 * @author Alexander Kovriga
 */
public class SiProfit implements Serializable {
    
    private static final long serialVersionUID = -2771061543671224890L;
    
    private int providerLocationId;
    private int serviceId;
    private double sum;

    public SiProfit() {
    }

    public int getProviderLocationId() {
        return providerLocationId;
    }

    public void setProviderLocationId(int providerLocationId) {
        this.providerLocationId = providerLocationId;
    }

    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }
    
}
