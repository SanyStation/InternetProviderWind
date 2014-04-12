/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.wind.dao.factory;

import com.netcracker.wind.dao.*;
import com.netcracker.wind.dao.impl.*;

/**
 *
 * @author Oksana
 */
public class DAOFactory {

    public static IUserDAO createUserDAO() {
        return new UserDAO();
    }

    public static IRoleDAO createRoleDAO() {
        return new RoleDAO();
    }

    public static ICableDAO createCableDAO() {
        return new CableDAO();
    }

    public static ICircuitDAO createCircuitDAO() {
        return new CircuitDAO();
    }

    public static IDeviceDAO createDeviceDAO() {
        return new DeviceDAO();
    }

    public static IPortDAO createPortDAO() {
        return new PortDAO();
    }

    public static IPriceDAO createPriceDAO() {
        return new PriceDAO();
    }

    public static IProviderLocationDAO createProviderLocationDAO() {
        return new ProviderLocationDAO();
    }

    public static IServiceDAO createServiceDAO() {
        return new ServiceDAO();
    }

    public static IServiceInstanceDAO createServiceInstanceDAO() {
        return new ServiceInstanceDAO();
    }

    public static IServiceLocationDAO createServiceLocationDAO() {
        return new ServiceLocationDAO();
    }

    public static IServiceOrderDAO createServiceOrderDAO() {
        return new ServiceOrderDAO();
    }

    public static ITaskDAO createTaskDAO() {
        return new TaskDAO();
    }

}
