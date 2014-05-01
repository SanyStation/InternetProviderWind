/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.netcracker.wind.dao.interfaces.csedashboard;

import java.util.List;

/**
 *
 * @author Anna
 */
public interface ICSEDashboardDAO <T> {
    
    public long getElementCount();
    
    public List<T> getElementsFromOffset(long count, long offset);
    
}
