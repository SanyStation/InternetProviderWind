package com.netcracker.wind.dao.reports.interfaces;

import com.netcracker.wind.entities.Device;
import java.util.List;

/**
 *
 * @author Alexander Kovriga
 */
public interface IDeviceUtilNCapDAO {
    
    public List<Device> find();
    
}
