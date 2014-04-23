package com.netcracker.wind.dao.interfaces.reports;

import com.netcracker.wind.entities.reports.RiMostProfRouter;
import java.util.List;

/**
 *
 * @author Alexander Kovriga
 */
public interface IRiMostProfRouterDAO {
    
    public List<RiMostProfRouter> findAll();
    
}
