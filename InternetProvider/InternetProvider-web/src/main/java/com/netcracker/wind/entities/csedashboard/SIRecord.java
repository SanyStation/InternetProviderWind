/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.wind.entities.csedashboard;

import com.netcracker.wind.dao.factory.AbstractFactoryDAO;
import com.netcracker.wind.dao.factory.FactoryCreator;
import com.netcracker.wind.dao.implementations.oracle.csedashboard.OracleCSESITableDAO;
import com.netcracker.wind.dao.interfaces.csedashboard.ICSEDashboardDAO;
import java.io.Serializable;
import java.util.List;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
    private final Logger LOGGER
            = Logger.getLogger(CSEDashboardEntity.class.getName());

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

    @Override
    public String parseJSON(List jsonList) {
        List<SIRecord> instances = jsonList;
        JSONArray instancesJSONArray = new JSONArray();
        for (SIRecord si : instances) {
            try {
                JSONObject siJSON = new JSONObject();
                siJSON.put("SI_id", si.getSi_id());
                siJSON.put("User_id", si.getUser_id());
                siJSON.put("User_name", si.getUser_name());
                siJSON.put("Service_order_id", si.getSo_id());
                siJSON.put("Service_id", si.getService_id());
                siJSON.put("SI_status", si.getService_name());
                siJSON.put("Service_id", si.getSi_status());
                instancesJSONArray.put(siJSON);
            } catch (JSONException ex) {
                LOGGER.error(null, ex);
            }
        }
        return instancesJSONArray.toString();
    }

}
