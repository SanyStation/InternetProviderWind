package com.netcracker.wind.dao.interfaces;

import com.netcracker.wind.entities.Service;
import java.util.List;

/**
 *
 * @author Oksana
 */
public interface IServiceDAO {

    public void add(Service service);

    public void delete(int idService);

    public Service findByID(int idService);

    public List<Service> findAll();

    public void update(Service role);
}
