package com.netcracker.wind.dao.interfaces;

import com.netcracker.wind.entities.Device;
import java.util.List;

/**
 *
 * @author Oksana
 */
public interface IDeviceDAO extends IRowsCounter {

    public void add(Device device);

    public void delete(int id);

    public Device findById(int id);

    public List<Device> findAll(int pageNumber, int pageSize);
    
    public void update(Device device);
    
    public Device findByName(String deviceName);

}
