package com.netcracker.wind.dao.interfaces;

import com.netcracker.wind.entities.Service;
import java.util.List;

/**
 * interface for working with Service entity and database
 * @author Oksana
 */
public interface IServiceDAO extends IRowsCounter {

    public void add(Service service);

    public void delete(int id);

    public Service findById(int id);

    public List<Service> findAll(int pageNumber, int pageSize);

    public void update(Service role);
    
}
