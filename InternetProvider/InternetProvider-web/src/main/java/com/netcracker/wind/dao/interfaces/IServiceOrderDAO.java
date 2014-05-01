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

    public ServiceOrder findByID(int role);

    public List<ServiceOrder> findByProvLoc(int pLID);

    public List<ServiceOrder> findByService(int idService);
    
    public List<ServiceOrder> findByServiceInstance(int serviceInstanceId);
    
    public List<ServiceOrder> findByUser(int userId);

    public List<ServiceOrder> findAll();

    public void update(ServiceOrder role);
}
