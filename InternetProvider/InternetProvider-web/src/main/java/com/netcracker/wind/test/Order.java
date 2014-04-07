package com.netcracker.wind.test;

/**
 *
 * @author Alexander Kovriga
 */
public class Order {
    
    private int id;
    private String service;
    private String date;
    private double cost;

    public Order(int id, String service, String date, double cost) {
        this.id = id;
        this.service = service;
        this.date = date;
        this.cost = cost;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }
    
    
    
}
