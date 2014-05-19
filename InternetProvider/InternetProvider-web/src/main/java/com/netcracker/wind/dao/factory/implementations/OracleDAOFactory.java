package com.netcracker.wind.dao.factory.implementations;

import com.netcracker.wind.dao.factory.AbstractFactoryDAO;
import com.netcracker.wind.dao.implementations.oracle.*;
import com.netcracker.wind.dao.implementations.oracle.reports.OracleCiaIptDAO;
import com.netcracker.wind.dao.implementations.oracle.reports.OracleRiMostProfRouterDAO;
import com.netcracker.wind.dao.implementations.oracle.reports.OracleRiRoutersUtilNCapDAO;
import com.netcracker.wind.dao.implementations.oracle.reports.OracleSiOrdersDAO;
import com.netcracker.wind.dao.implementations.oracle.reports.OracleSiProfitDAO;
import com.netcracker.wind.dao.interfaces.*;
import com.netcracker.wind.dao.interfaces.reports.ICiaIptDAO;
import com.netcracker.wind.dao.interfaces.reports.IRiMostProfRouterDAO;
import com.netcracker.wind.dao.interfaces.reports.IRiRoutersUtilNCapDAO;
import com.netcracker.wind.dao.interfaces.reports.ISiOrdersDAO;
import com.netcracker.wind.dao.interfaces.reports.ISiProfitDAO;

/**
 * this class is an implementation of DAO factory for Oracle
 * @author Oksana
 */
public class OracleDAOFactory extends AbstractFactoryDAO {

    @Override
    public IUserDAO createUserDAO() {
        return new OracleUserDAO();
    }

    @Override
    public IRoleDAO createRoleDAO() {
        return new OracleRoleDAO();
    }

    @Override
    public ICableDAO createCableDAO() {
        return new OracleCableDAO();
    }

    @Override
    public ICircuitDAO createCircuitDAO() {
        return new OracleCircuitDAO();
    }

    @Override
    public IDeviceDAO createDeviceDAO() {
        return new OracleDeviceDAO();
    }

    @Override
    public IPortDAO createPortDAO() {
        return new OraclePortDAO();
    }

    @Override
    public IPriceDAO createPriceDAO() {
        return new OraclePriceDAO();
    }

    @Override
    public IProviderLocationDAO createProviderLocationDAO() {
        return new OracleProviderLocationDAO();
    }

    @Override
    public IServiceDAO createServiceDAO() {
        return new OracleServiceDAO();
    }

    @Override
    public IServiceInstanceDAO createServiceInstanceDAO() {
        return new OracleServiceInstanceDAO();
    }

    @Override
    public IServiceLocationDAO createServiceLocationDAO() {
        return new OracleServiceLocationDAO();
    }

    @Override
    public IServiceOrderDAO createServiceOrderDAO() {
        return new OracleServiceOrderDAO();
    }

    @Override
    public ITaskDAO createTaskDAO() {
        return new OracleTaskDAO();
    }
    
    @Override
    public ICiaIptDAO createCiaIptDAO() {
        return new OracleCiaIptDAO();
    }
    
    @Override
    public IRiMostProfRouterDAO createRiMostProfRouterDAO() {
        return new OracleRiMostProfRouterDAO();
    }
    
    @Override
    public IRiRoutersUtilNCapDAO createRiRoutersUtilNCapDAO() {
        return new OracleRiRoutersUtilNCapDAO();
    }
    
    @Override
    public ISiOrdersDAO createSiNewOrdersDAO() {
        return new OracleSiOrdersDAO(ISiOrdersDAO.Scenario.NEW);
    }
    
    @Override
    public ISiOrdersDAO createSiDiscOrdersDAO() {
        return new OracleSiOrdersDAO(ISiOrdersDAO.Scenario.DISCONNECT);
    }
    
    @Override
    public ISiProfitDAO createSiProfByMonthDAO() {
        return new OracleSiProfitDAO();
    }

}
