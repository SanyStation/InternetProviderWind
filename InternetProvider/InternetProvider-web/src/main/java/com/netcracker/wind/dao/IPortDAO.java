/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.wind.dao;

import com.netcracker.wind.entities.Port;

/**
 *
 * @author Oksana
 */
public interface IPortDAO {

    public void add(Port port);

    public void delete(int idPort);

    public Port findByID(int idPort);

    public void update(Port port);
}
