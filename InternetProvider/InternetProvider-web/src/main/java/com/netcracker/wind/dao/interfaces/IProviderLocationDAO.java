package com.netcracker.wind.dao.interfaces;

import com.netcracker.wind.entities.ProviderLocation;
import java.util.List;

/**
 *
 * @author Oksana
 */
public interface IProviderLocationDAO extends IRowsCounter {

    public void add(ProviderLocation providerLocation);

    public void delete(int id);

    public ProviderLocation findById(int id);
    
    public List<ProviderLocation> findAll(int pageNumber, int pageSize);

    public void update(ProviderLocation providerLocation);
    
}
