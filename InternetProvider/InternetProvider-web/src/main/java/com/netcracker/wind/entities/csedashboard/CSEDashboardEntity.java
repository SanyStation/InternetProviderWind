/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.netcracker.wind.entities.csedashboard;

import java.util.List;

/**
 *
 * @author Anna
 */
public abstract class CSEDashboardEntity <T>{

    /**
     *
     * @param jsonList
     * @return
     */
    public abstract String parseJSON(List<T> jsonList);
}
