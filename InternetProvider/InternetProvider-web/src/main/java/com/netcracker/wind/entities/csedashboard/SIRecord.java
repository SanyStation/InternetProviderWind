/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.wind.entities.csedashboard;

import java.io.Serializable;

/**
 *
 * @author Anna
 */
public class SIRecord extends CSEDashboardEntity implements Serializable {

    private static final long serialVersionUID = 10275539472837495L;

    private int si_id;
    private int user_id;
    private String user_name;
    private int so_id;
    private int service_id;
    private String service_name;
    private String si_status;

    public SIRecord() {
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public int getSi_id() {
        return si_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public String getUser_name() {
        return user_name;
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

    public String getSi_status() {
        return si_status;
    }

    public void setSi_id(int si_id) {
        this.si_id = si_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
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

    public void setSi_status(String si_status) {
        this.si_status = si_status;
    }

}
