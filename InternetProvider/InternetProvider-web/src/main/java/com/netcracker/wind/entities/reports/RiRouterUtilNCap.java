package com.netcracker.wind.entities.reports;

/**
 *
 * @author Alexander Kovriga
 */
public class RiRouterUtilNCap {

    private int id;
    private int capacity;
    private int utilization;
    
    public RiRouterUtilNCap() {
    }

    public RiRouterUtilNCap(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
    
    public double getUtilizationPercent() {
        return capacity > 0 ? (double) utilization / capacity : 0;
    }
    
}
