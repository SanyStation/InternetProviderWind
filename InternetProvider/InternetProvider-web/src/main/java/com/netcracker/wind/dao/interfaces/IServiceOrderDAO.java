package com.netcracker.wind.dao.interfaces;

import com.netcracker.wind.entities.ServiceOrder;
import java.util.List;

/**
 *
 * @author Oksana
 */
public interface IServiceOrderDAO {

    public void add(ServiceOrder serviceOrder);

    public void delete(int id);

    public ServiceOrder findById(int id);

    public List<ServiceOrder> findByProvLoc(int plId, int pageNumber,
            int pageSize);

    public List<ServiceOrder> findByService(int serviceId, int pageNumber,
            int pageSize);
    
    public List<ServiceOrder> findByServiceInstance(int serviceInstanceId,
            int pageNumber, int pageSize);
    
    public List<ServiceOrder> findByUser(int userId, int pageNumber,
            int pageSize);

    public List<ServiceOrder> findAll(int pageNumber, int pageSize);

    public void update(ServiceOrder serviceOrder);
    
    public int getRows();
    
}
