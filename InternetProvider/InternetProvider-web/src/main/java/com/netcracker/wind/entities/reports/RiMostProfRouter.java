package com.netcracker.wind.entities.reports;

import java.io.Serializable;

/**
 *
 * @author Anatolii
 */
public class RiMostProfRouter implements Serializable {
    
    private static final long serialVersionUID = 2119276325841852175L;

    private int id;
    private String name;
    private double profit;

    public RiMostProfRouter() {
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

    public double getProfit() {
        return profit;
    }

    public void setProfit(double profit) {
        this.profit = profit;
    }

}
