package com.netcracker.wind.dao.factory;

import com.netcracker.wind.dao.interfaces.*;
import com.netcracker.wind.dao.reports.interfaces.*;

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
    public abstract ICiaIptDAO createCiaIptDAO();
    public abstract IRiMostProfRouterDAO createRiMostProfRouterDAO();
    public abstract IRiRoutersUtilNCapDAO createRiRoutersUtilNCapDAO();
    public abstract ISiDisconnOrdersDAO createSiDisconnOrdersDAO();
    public abstract ISiNewOrdersDAO createSiNewOrdersDAO();
    public abstract ISiProfByMonthDAO createSiProfByMonthDAO();
    
}
