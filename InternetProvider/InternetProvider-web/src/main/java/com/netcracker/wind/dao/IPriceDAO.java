/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.wind.dao;

import com.netcracker.wind.entities.Price;

/**
 *
 * @author Oksana
 */
public interface IPriceDAO {

    public void add(Price price);

    public void delete(int idPrice);

    public Price findByID(int idPrice);

    public void update(Price price);
}