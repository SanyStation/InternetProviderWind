/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.wind.dao.factory.impl;

import com.netcracker.wind.dao.*;
import com.netcracker.wind.dao.factory.AbstractFactoryDAO;
import com.netcracker.wind.dao.impl.*;

/**
 *
 * @author Oksana
 */
public class OracleDAOFactory extends AbstractFactoryDAO {

    public IUserDAO createUserDAO() {
        return new UserDAO();
    }

    public IRoleDAO createRoleDAO() {
        return new RoleDAO();
    }

    public ICableDAO createCableDAO() {
        return new CableDAO();
    }

    public ICircuitDAO createCircuitDAO() {
        return new CircuitDAO();
    }

    public IDeviceDAO createDeviceDAO() {
        return new DeviceDAO();
    }

    public IPortDAO createPortDAO() {
        return new PortDAO();
    }

    public IPriceDAO createPriceDAO() {
        return new PriceDAO();
    }

    public IProviderLocationDAO createProviderLocationDAO() {
        return new ProviderLocationDAO();
    }

    public IServiceDAO createServiceDAO() {
        return new ServiceDAO();
    }

    public IServiceInstanceDAO createServiceInstanceDAO() {
        return new ServiceInstanceDAO();
    }

    public IServiceLocationDAO createServiceLocationDAO() {
        return new ServiceLocationDAO();
    }

    public IServiceOrderDAO createServiceOrderDAO() {
        return new ServiceOrderDAO();
    }

    public ITaskDAO createTaskDAO() {
        return new TaskDAO();
    }

}
