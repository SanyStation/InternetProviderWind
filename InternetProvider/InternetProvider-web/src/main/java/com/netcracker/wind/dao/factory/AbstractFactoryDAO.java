/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.wind.dao.factory;

import com.netcracker.wind.dao.interfaces.ICableDAO;
import com.netcracker.wind.dao.interfaces.ICircuitDAO;
import com.netcracker.wind.dao.interfaces.IDeviceDAO;
import com.netcracker.wind.dao.interfaces.IPortDAO;
import com.netcracker.wind.dao.interfaces.IPriceDAO;
import com.netcracker.wind.dao.interfaces.IProviderLocationDAO;
import com.netcracker.wind.dao.interfaces.IRoleDAO;
import com.netcracker.wind.dao.interfaces.IServiceDAO;
import com.netcracker.wind.dao.interfaces.IServiceInstanceDAO;
import com.netcracker.wind.dao.interfaces.IServiceLocationDAO;
import com.netcracker.wind.dao.interfaces.IServiceOrderDAO;
import com.netcracker.wind.dao.interfaces.ITaskDAO;
import com.netcracker.wind.dao.interfaces.IUserDAO;

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
