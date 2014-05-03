package com.netcracker.wind.dao.interfaces;

import com.netcracker.wind.entities.Price;
import java.util.List;

/**
 *
 * @author Oksana
 */
public interface IPriceDAO {

    public void add(Price price);

    public void delete(int id);

    public Price findById(int id);

    public List<Price> findByProviderLoc(int plId, int pageNumber,
            int pageSize);

    public List<Price> findByService(int serviceId, int pageNumber,
            int pageSize);

    public List<Price> findAll(int pageNumber, int pageSize);

    public void update(Price price);
    
    public int getRows();
    
}
