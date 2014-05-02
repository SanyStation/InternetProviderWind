package com.netcracker.wind.dao.interfaces;

import com.netcracker.wind.entities.ServiceInstance;
import java.util.List;

/**
 *
 * @author Oksana
 */
public interface IServiceInstanceDAO {

    public void add(ServiceInstance serviceInstance);

    public void delete(int idSI);

    public ServiceInstance findByID(int idSI);

    public List<ServiceInstance> findByService(int idService);

    public List<ServiceInstance> findAll();

    public ServiceInstance findByServiceOrderId(int idOrder);
    
    public List<ServiceInstance> findByUser(int userId);

    public void update(ServiceInstance serviceInstance);
}
