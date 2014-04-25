package com.netcracker.wind.entities.reports;

import java.io.Serializable;

/**
 *
 * @author Anatolii
 */
public class RiMostProfRouter implements Serializable {
    
    private static final long serialVersionUID = 2119276325841852175L;

    private int id;
    private Integer profit;

    public RiMostProfRouter() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getProfit() {
        return profit;
    }

    public void setProfit(Integer profit) {
        this.profit = profit;
    }

}
