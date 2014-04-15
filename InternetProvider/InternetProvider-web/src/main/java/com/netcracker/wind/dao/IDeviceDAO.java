/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.wind.dao;

import com.netcracker.wind.entities.Device;
import java.util.List;

/**
 *
 * @author Oksana
 */
public interface IDeviceDAO {

    public void add(Device device);

    public void delete(int idDevice);

    public Device findByID(int idDevice);
    public List<Device> findAll();

    //just one id - nothing to update
    //public void update(Device device);

}
