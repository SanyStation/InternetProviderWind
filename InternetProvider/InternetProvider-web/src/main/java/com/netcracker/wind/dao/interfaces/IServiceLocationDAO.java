package com.netcracker.wind.dao.interfaces;

import com.netcracker.wind.entities.ServiceLocation;
import java.util.List;

/**
 *
 * @author Oksana
 */
public interface IServiceLocationDAO extends IRowsCounter {

    public void add(ServiceLocation serviceLocation);

    public void delete(int id);

    public ServiceLocation findById(int id);

    public void update(ServiceLocation serviceLocation);

    public List<ServiceLocation> findAll(int pageNumber, int pageSize);
    
}
