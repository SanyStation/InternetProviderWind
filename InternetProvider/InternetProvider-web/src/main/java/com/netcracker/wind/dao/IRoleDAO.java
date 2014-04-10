/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.netcracker.wind.dao;

import com.netcracker.wind.entities.Role;

/**
 *
 * @author Oksana
 */
public interface IRoleDAO {
    public void add(Role role);
    public void delete(int id);
    public Role findByID(int role);
    public void update(Role role);
    
}
