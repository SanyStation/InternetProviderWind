package com.netcracker.wind.dao.interfaces.reports;

import com.netcracker.wind.entities.ServiceOrder;
import java.util.List;

/**
 *
 * @author Alexander Kovriga
 */
public interface ISiDisconnOrdersDAO {
    
    public List<ServiceOrder> find();
    
}
