package com.netcracker.wind.dao.interfaces;

import com.netcracker.wind.entities.Cable;
import java.util.List;

/**
 * interface for working with Cable entity and database
 * @author Oksana
 */
public interface ICableDAO extends IRowsCounter {

    public void add(Cable cable);

    public void delete(int id);

    public Cable findById(int id);

    public Cable findByPort(int portId);

    public List<Cable> findAll(int pageNumber, int pageSize);

    public Cable findByServiceLocation(int slId);

    public void update(Cable cable);
    
}
