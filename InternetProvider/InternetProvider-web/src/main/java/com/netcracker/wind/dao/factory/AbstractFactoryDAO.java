package com.netcracker.wind.dao.factory;

import com.netcracker.wind.dao.interfaces.reports.ISiProfitDAO;
import com.netcracker.wind.dao.interfaces.reports.IRiRoutersUtilNCapDAO;
import com.netcracker.wind.dao.interfaces.reports.ISiOrdersDAO;
import com.netcracker.wind.dao.interfaces.reports.ICiaIptDAO;
import com.netcracker.wind.dao.interfaces.reports.IRiMostProfRouterDAO;
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

    public abstract ICiaIptDAO createCiaIptDAO();

    public abstract IRiMostProfRouterDAO createRiMostProfRouterDAO();

    public abstract IRiRoutersUtilNCapDAO createRiRoutersUtilNCapDAO();

    public abstract ISiProfitDAO createSiProfByMonthDAO();

    public abstract ISiOrdersDAO createSiNewOrdersDAO();

    public abstract ISiOrdersDAO createSiDisconnOrdersDAO();

}
