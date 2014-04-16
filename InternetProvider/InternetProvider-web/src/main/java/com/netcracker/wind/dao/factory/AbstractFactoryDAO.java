/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.wind.dao.factory;

import com.netcracker.wind.dao.ICableDAO;
import com.netcracker.wind.dao.ICircuitDAO;
import com.netcracker.wind.dao.IDeviceDAO;
import com.netcracker.wind.dao.IPortDAO;
import com.netcracker.wind.dao.IPriceDAO;
import com.netcracker.wind.dao.IProviderLocationDAO;
import com.netcracker.wind.dao.IRoleDAO;
import com.netcracker.wind.dao.IServiceDAO;
import com.netcracker.wind.dao.IServiceInstanceDAO;
import com.netcracker.wind.dao.IServiceLocationDAO;
import com.netcracker.wind.dao.IServiceOrderDAO;
import com.netcracker.wind.dao.ITaskDAO;
import com.netcracker.wind.dao.IUserDAO;

/**
 *
 * @author Anatolii
 */
public abstract class AbstractFactoryDAO {

    public abstract IUserDAO createUserDAO();

    public abstract IRoleDAO createRoleDAO();

    public abstract ICableDAO createCableDAO();

    public abstract ICircuitDAO createCircuitDAO();

    public abstract IDeviceDAO createDeviceDAO();

    public abstract IPortDAO createPortDAO();

    public abstract IPriceDAO createPriceDAO();

    public abstract IProviderLocationDAO createProviderLocationDAO();

    public abstract IServiceDAO createServiceDAO();

    public abstract IServiceInstanceDAO createServiceInstanceDAO();

    public abstract IServiceLocationDAO createServiceLocationDAO();

    public abstract IServiceOrderDAO createServiceOrderDAO();

    public abstract ITaskDAO createTaskDAO();
}
