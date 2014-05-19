package com.netcracker.wind.dao.interfaces;

import com.netcracker.wind.dao.implementations.helper.AbstractOracleDAO.Direction;
import com.netcracker.wind.entities.ServiceInstance;
import java.util.List;

/**
 * interface for working with Service Instance entity and database
 * @author Oksana
 */
public interface IServiceInstanceDAO extends IRowsCounter {

    public void add(ServiceInstance serviceInstance);

    public void delete(int id);

    public ServiceInstance findById(int id);

    public List<ServiceInstance> findByService(int serviceId, int pageNumber,
            int pageSize);

    public List<ServiceInstance> findAll(int pageNumber, int pageSize);

    public ServiceInstance findByServiceOrder(int soId);

    public List<ServiceInstance> findByUser(int userId, int pageNumber,
            int pageSize);
    
    public List<ServiceInstance> findByUser(int userId, int pageNumber,
            int pageSize, String orderParam, Direction direction);

    public void update(ServiceInstance serviceInstance);
    
}
