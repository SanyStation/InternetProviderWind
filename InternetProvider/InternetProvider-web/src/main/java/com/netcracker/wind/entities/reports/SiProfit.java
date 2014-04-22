package com.netcracker.wind.entities.reports;

/**
 *
 * @author Alexander Kovriga
 */
public class SiProfit {
    
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
