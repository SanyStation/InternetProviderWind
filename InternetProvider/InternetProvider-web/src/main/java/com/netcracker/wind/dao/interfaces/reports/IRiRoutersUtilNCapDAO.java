package com.netcracker.wind.dao.interfaces.reports;

import com.netcracker.wind.entities.reports.RiRouterUtilNCap;
import java.util.List;

/**
 * The {@code IRiRoutersUtilNCapDAO} interface designed to provide interface for
 * working with persistence to manipulate {@code RiRoutersUtilNCap} entities.
 * 
 * @author Alexander Kovriga
 */
public interface IRiRoutersUtilNCapDAO {
    
    /**
     * Finds all routers and their utilization and capacity.
     * 
     * @return list of routers
     */
    public List<RiRouterUtilNCap> findAll();
    
}
