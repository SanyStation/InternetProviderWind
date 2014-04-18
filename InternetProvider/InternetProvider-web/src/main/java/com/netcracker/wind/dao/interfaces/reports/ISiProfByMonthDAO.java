package com.netcracker.wind.dao.interfaces.reports;

import com.netcracker.wind.entities.Device;
import java.util.List;

/**
 *
 * @author Alexander Kovriga
 */
public interface ISiProfByMonthDAO {
    
    public List<Device> find();
    
}
