package com.netcracker.wind.dao.reports.interfaces;

import com.netcracker.wind.entities.ServiceOrder;
import java.util.List;

/**
 *
 * @author Alexander Kovriga
 */
public interface IDisconnOrdersDAO {
    
    public List<ServiceOrder> find();
    
}
