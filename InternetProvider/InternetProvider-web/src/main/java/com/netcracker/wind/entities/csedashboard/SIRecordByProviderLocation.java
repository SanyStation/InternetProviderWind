/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.wind.entities.csedashboard;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Anna
 */
public class SIRecordByProviderLocation extends CSEDashboardEntity implements Serializable {

    private static final long serialVersionUID = 7526472295622776147L;

    private int provLoc_id;
    private int si_id;
    private String si_status;
    private int so_id;
    private int service_id;
    private String service_name;

    public SIRecordByProviderLocation() {
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public int getProvLoc_id() {
        return provLoc_id;
    }

    public int getSi_id() {
        return si_id;
    }

    public String getSi_status() {
        return si_status;
    }

    public int getSo_id() {
        return so_id;
    }

    public int getService_id() {
        return service_id;
    }

    public String getService_name() {
        return service_name;
    }

    public void setProvLoc_id(int provLoc_id) {
        this.provLoc_id = provLoc_id;
    }

    public void setSi_id(int si_id) {
        this.si_id = si_id;
    }

    public void setSi_status(String si_status) {
        this.si_status = si_status;
    }

    public void setSo_id(int so_id) {
        this.so_id = so_id;
    }

    public void setService_id(int service_id) {
        this.service_id = service_id;
    }

    public void setService_name(String service_name) {
        this.service_name = service_name;
    }

    @Override
    public String parseJSON(List jsonList) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
