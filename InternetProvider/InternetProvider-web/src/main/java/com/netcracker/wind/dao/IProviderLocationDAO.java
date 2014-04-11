/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.netcracker.wind.dao;

import com.netcracker.wind.entities.ProviderLocation;

/**
 *
 * @author Oksana
 */
public interface IProviderLocationDAO {
      public void add(ProviderLocation providerLocation);
    public void delete(int idPL);
    public ProviderLocation findByID(int idPL);
    public void update(ProviderLocation providerLocation);
}
