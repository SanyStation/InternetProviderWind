package com.netcracker.wind.dao.interfaces;

import com.netcracker.wind.entities.Price;
import java.util.List;

/**
 *
 * @author Oksana
 */
public interface IPriceDAO {

    public void add(Price price);

    public void delete(int idPrice);

    public Price findByID(int idPrice);

    public List<Price> findByProviderLoc(int idPLoc);

    public List<Price> findByService(int idService);

    public List<Price> findAll();

    public void update(Price price);
}
