package com.netcracker.wind.dao.interfaces;

import com.netcracker.wind.entities.Cable;
import java.util.List;

/**
 *
 * @author Oksana
 */
public interface ICableDAO {

    public void add(Cable cable);

    public void delete(int id);

    public Cable findById(int id);

    public Cable findByPort(int portId);

    public List<Cable> findAll(int pageNumber, int pageSize);

    public Cable findByServiceLocation(int slId);

    public void update(Cable cable);
    
    public int getRows();
    
}
