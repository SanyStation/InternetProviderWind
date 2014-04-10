/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.wind.entities;

import java.io.Serializable;

/**
 *
 * @author Anatolii
 */
public class Cable implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer id;
    private ServiceInstance serviceInstances;
    private Port ports;

    public Cable() {
    }

    public Cable(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ServiceInstance getServiceInstances() {
        return serviceInstances;
    }

    public void setServiceInstances(ServiceInstance serviceInstances) {
        this.serviceInstances = serviceInstances;
    }

    public Port getPorts() {
        return ports;
    }

    public void setPorts(Port ports) {
        this.ports = ports;
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
        if (!(object instanceof Cable)) {
            return false;
        }
        Cable other = (Cable) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.netcracker.wind.entities.Cables[ id=" + id + " ]";
    }

}
