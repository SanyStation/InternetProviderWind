/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.wind.dao;

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
    
    public void update(ServiceInstance serviceInstance);
}
