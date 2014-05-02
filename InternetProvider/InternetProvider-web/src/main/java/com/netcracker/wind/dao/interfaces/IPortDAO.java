package com.netcracker.wind.dao.interfaces;

import com.netcracker.wind.entities.Port;
import java.util.List;

/**
 *
 * @author Oksana
 */
public interface IPortDAO {

    public void add(Port port);

    public void delete(int idPort);

    public Port findByID(int idPort);

    public List<Port> findByFree(boolean isFree);

    public List<Port> findAll();

    public void update(Port port);

    public List<Port> findByDevice(int idDevice);

    public Port occupyFreePort();
}
