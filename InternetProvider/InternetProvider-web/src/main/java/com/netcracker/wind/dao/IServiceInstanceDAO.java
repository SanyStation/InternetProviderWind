/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.netcracker.wind.dao;

import com.netcracker.wind.entities.ServiceInstance;

/**
 *
 * @author Oksana
 */
public interface IServiceInstanceDAO {
    public void add(ServiceInstance serviceInstance);
    public void delete(int idSI);
    public ServiceInstance findByID(int idSI);
    public void update(ServiceInstance serviceInstance);
}
