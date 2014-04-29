package com.netcracker.wind.entities.reports;

import java.io.Serializable;

/**
 *
 * @author Alexander Kovriga
 */
public class CiaIpt implements Serializable {

    private static final long serialVersionUID = 2459276325845312112L;

    private int deviceId;
    private String deviceName;
    private int portId;
    private int circuitId;
    private int serviceInstanceId;

    public CiaIpt() {
    }

    public int getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(int deviceId) {
        this.deviceId = deviceId;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public int getPortId() {
        return portId;
    }

    public void setPortId(int portId) {
        this.portId = portId;
    }

    public int getCircuitId() {
        return circuitId;
    }

    public void setCircuitId(int circuitId) {
        this.circuitId = circuitId;
    }

    public int getServiceInstanceId() {
        return serviceInstanceId;
    }

    public void setServiceInstanceId(int serviceInstanceId) {
        this.serviceInstanceId = serviceInstanceId;
    }
    

}
