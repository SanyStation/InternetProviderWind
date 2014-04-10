/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.wind.entities;

import java.io.Serializable;
import java.util.Collection;

/**
 *
 * @author Anatolii
 */
public class Port implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer id;
    private Character free;
    private Device devices;
    private Circuit circuits;
    private Collection<Cable> cablesCollection;

    public Port() {
    }

    public Port(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Character getFree() {
        return free;
    }

    public void setFree(Character free) {
        this.free = free;
    }

    public Device getDevices() {
        return devices;
    }

    public void setDevices(Device devices) {
        this.devices = devices;
    }

    public Circuit getCircuits() {
        return circuits;
    }

    public void setCircuits(Circuit circuits) {
        this.circuits = circuits;
    }

    public Collection<Cable> getCablesCollection() {
        return cablesCollection;
    }

    public void setCablesCollection(Collection<Cable> cablesCollection) {
        this.cablesCollection = cablesCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Port)) {
            return false;
        }
        Port other = (Port) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.netcracker.wind.entities.Ports[ id=" + id + " ]";
    }

}
