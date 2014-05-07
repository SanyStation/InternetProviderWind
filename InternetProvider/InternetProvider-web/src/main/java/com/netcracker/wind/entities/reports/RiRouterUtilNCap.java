package com.netcracker.wind.entities.reports;

import java.io.Serializable;

/**
 *
 * @author Alexander Kovriga
 */
public class RiRouterUtilNCap implements Serializable {
    
    private static final long serialVersionUID = -8044636653088120971L;

    private int id;
    private String name;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        return capacity > 0 ? (double) utilization / capacity * 100 : 0;
    }
    
}
