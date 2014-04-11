/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.wind.dao;

import com.netcracker.wind.entities.Cable;

/**
 *
 * @author Oksana
 */
public interface ICableDAO {

    public void add(Cable cable);

    public void delete(int idCable);

    public Cable findByID(int idCable);

    public void update(Cable cable);
}
