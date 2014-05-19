package com.netcracker.wind.dao.interfaces.reports;

import com.netcracker.wind.entities.reports.RiMostProfRouter;
import java.util.List;

/**
 * The {@code IRiMostProfRouterDAO} interface designed to provide interface for
 * working with persistence to manipulate {@code RiMostProfRouter} entities.
 * 
 * @author Alexander Kovriga
 */
public interface IRiMostProfRouterDAO {
    
    /**
     * Finds all routers and their profit for the whole period sorted in 
     * the descending order.
     * 
     * @return list of routers
     */
    public List<RiMostProfRouter> findAll();
    
}
