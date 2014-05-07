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
    private String portName;
    private String portStatus;
    private int circuitId;
    private String circuitName;
    private int serviceInstanceId;
    private String serviceInstanceName;

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

    public String getPortName() {
        return portName;
    }

    public void setPortName(String portName) {
        this.portName = portName;
    }

    public String getPortStatus() {
        return portStatus;
    }

    public void setPortStatus(String portStatus) {
        this.portStatus = portStatus;
    }
    
    public int getCircuitId() {
        return circuitId;
    }

    public void setCircuitId(int circuitId) {
        this.circuitId = circuitId;
    }

    public String getCircuitName() {
        return circuitName;
    }

    public void setCircuitName(String circuitName) {
        this.circuitName = circuitName;
    }
    
    public int getServiceInstanceId() {
        return serviceInstanceId;
    }

    public void setServiceInstanceId(int serviceInstanceId) {
        this.serviceInstanceId = serviceInstanceId;
    }

    public String getServiceInstanceName() {
        return serviceInstanceName;
    }

    public void setServiceInstanceName(String serviceInstanceName) {
        this.serviceInstanceName = serviceInstanceName;
    }
    

}
