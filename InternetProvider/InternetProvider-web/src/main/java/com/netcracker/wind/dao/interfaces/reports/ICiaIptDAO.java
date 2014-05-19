package com.netcracker.wind.dao.interfaces.reports;

import com.netcracker.wind.entities.reports.CiaIpt;
import java.util.List;

/**
 * The {@code ICiaIptDAO} interface designed to provide interface for working
 * with persistence to manipulate {@code CiaIpt} entities.
 * 
 * @author Alexander Kovriga
 */
public interface ICiaIptDAO {
    
    /**
     * Finds all routers, their ports, circuits that connected to the ports and
     * service instances that linked to the ports via the circuits.
     * 
     * @return list of routers and their links
     */
    public List<CiaIpt> findAll();
    
}
