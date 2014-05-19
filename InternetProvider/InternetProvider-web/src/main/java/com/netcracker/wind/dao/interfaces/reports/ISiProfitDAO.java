package com.netcracker.wind.dao.interfaces.reports;

import com.netcracker.wind.entities.reports.SiProfit;
import java.util.List;

/**
 * The {@code ISiProfitDAO} interface designed to provide interface for working
 * with persistence to manipulate {@code SiProfit} entities.
 * 
 * @author Alexander Kovriga
 */
public interface ISiProfitDAO {
    
    /**
     * Finds profit by certain month.
     * 
     * @param dateTo first date of certain month
     * @return list of profits per certain provider locations and services
     */
    public List<SiProfit> findByDateTo(String dateTo);
    
}
