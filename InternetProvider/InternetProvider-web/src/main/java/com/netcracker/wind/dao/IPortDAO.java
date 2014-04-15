/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.wind.dao;

import com.netcracker.wind.entities.Port;
import java.util.List;

/**
 *
 * @author Oksana
 */
public interface IPortDAO {

    public void add(Port port);

    public void delete(int idPort);

    public Port findByID(int idPort);
    public List<Port> findAll();

    public void update(Port port);
    public List<Port> findByDevice(int idDevice);
}
