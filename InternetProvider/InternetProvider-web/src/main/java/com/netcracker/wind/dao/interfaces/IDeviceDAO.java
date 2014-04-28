package com.netcracker.wind.dao.interfaces;

import com.netcracker.wind.entities.Device;
import java.util.List;

/**
 *
 * @author Oksana
 */
public interface IDeviceDAO {

    public void add(Device device);

    public void delete(int idDevice);

    public Device findByID(int idDevice);
    public List<Device> findAll();

    //just one id - nothing to update
    //public void update(Device device);

    public Device findByName(String dName);

}
