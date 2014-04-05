package com.netcracker.wind.reports.test;

/**
 *
 * @author Alexander Kovriga
 */
public class Router {
    
    private String model;
    private int capacity;
    private int utilization;

    public Router(String model, int capacity) {
        this.model = model;
        this.capacity = capacity;
    }

    public Router(String model, int capacity, int utilization) {
        this.model = model;
        this.capacity = capacity;
        this.utilization = utilization;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getUtilization() {
        return utilization;
    }

    public void setUtilization(int utilization) {
        this.utilization = utilization;
    }
    
}
