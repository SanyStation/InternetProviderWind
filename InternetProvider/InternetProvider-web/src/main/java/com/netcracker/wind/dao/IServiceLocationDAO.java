/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.netcracker.wind.dao;

import com.netcracker.wind.entities.ServiceLocation;

/**
 *
 * @author Oksana
 */
public interface IServiceLocationDAO {
      public void add(ServiceLocation serviceLocation);
    public void delete(int id);
    public ServiceLocation findByID(int role);
    public void update(ServiceLocation serviceLocation);
}
