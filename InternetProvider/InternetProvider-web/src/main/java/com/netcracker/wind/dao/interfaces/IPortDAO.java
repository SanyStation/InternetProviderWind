package com.netcracker.wind.dao.interfaces;

import com.netcracker.wind.entities.Port;
import java.util.List;

/**
 *
 * @author Oksana
 */
public interface IPortDAO extends IRowsCounter {

    public void add(Port port);

    public void delete(int id);

    public Port findById(int id);

    public List<Port> findByFree(boolean isFree, int pageNumber, int pageSize);

    public List<Port> findAll(int pageNumber, int pageSize);

    public void update(Port port);

    public List<Port> findByDevice(int deviceId, int pageNumber, int pageSize);

    public Port occupyFreePort();
    
    public Port getFreePort();
    
}
