package com.netcracker.wind.dao.factory;

import com.netcracker.wind.dao.interfaces.*;

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
