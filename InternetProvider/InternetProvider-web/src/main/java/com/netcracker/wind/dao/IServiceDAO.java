/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.netcracker.wind.dao;

import com.netcracker.wind.entities.Service;

/**
 *
 * @author Oksana
 */
public interface IServiceDAO {
    public void add(Service service);
    public void delete(int idService);
    public Service findByID(int idService);
    public void update(Service role);
}
