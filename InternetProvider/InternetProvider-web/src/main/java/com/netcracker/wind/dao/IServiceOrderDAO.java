/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.wind.dao;

import com.netcracker.wind.entities.ServiceOrder;

/**
 *
 * @author Oksana
 */
public interface IServiceOrderDAO {

    public void add(ServiceOrder serviceOrder);

    public void delete(int id);

    public ServiceOrder findByID(int role);

    public void update(ServiceOrder role);
}
