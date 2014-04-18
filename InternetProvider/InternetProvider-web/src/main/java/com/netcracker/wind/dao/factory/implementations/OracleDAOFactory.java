package com.netcracker.wind.dao.factory.implementations;

import com.netcracker.wind.dao.interfaces.IPortDAO;
import com.netcracker.wind.dao.interfaces.IServiceInstanceDAO;
import com.netcracker.wind.dao.interfaces.IServiceDAO;
import com.netcracker.wind.dao.interfaces.IRoleDAO;
import com.netcracker.wind.dao.interfaces.IServiceOrderDAO;
import com.netcracker.wind.dao.interfaces.ITaskDAO;
import com.netcracker.wind.dao.interfaces.IServiceLocationDAO;
import com.netcracker.wind.dao.interfaces.IDeviceDAO;
import com.netcracker.wind.dao.interfaces.IProviderLocationDAO;
import com.netcracker.wind.dao.interfaces.ICircuitDAO;
import com.netcracker.wind.dao.interfaces.IUserDAO;
import com.netcracker.wind.dao.interfaces.IPriceDAO;
import com.netcracker.wind.dao.interfaces.ICableDAO;
import com.netcracker.wind.dao.implementations.oracle.OracleTaskDAO;
import com.netcracker.wind.dao.implementations.oracle.OraclePortDAO;
import com.netcracker.wind.dao.implementations.oracle.OracleProviderLocationDAO;
import com.netcracker.wind.dao.implementations.oracle.OracleCableDAO;
import com.netcracker.wind.dao.implementations.oracle.OracleServiceInstanceDAO;
import com.netcracker.wind.dao.implementations.oracle.OracleServiceDAO;
import com.netcracker.wind.dao.implementations.oracle.OracleCircuitDAO;
import com.netcracker.wind.dao.implementations.oracle.OracleUserDAO;
import com.netcracker.wind.dao.implementations.oracle.OraclePriceDAO;
import com.netcracker.wind.dao.implementations.oracle.OracleServiceLocationDAO;
import com.netcracker.wind.dao.implementations.oracle.OracleDeviceDAO;
import com.netcracker.wind.dao.implementations.oracle.OracleRoleDAO;
import com.netcracker.wind.dao.implementations.oracle.OracleServiceOrderDAO;
import com.netcracker.wind.dao.factory.AbstractFactoryDAO;

/**
 *
 * @author Oksana
 */
public class OracleDAOFactory extends AbstractFactoryDAO {

    public IUserDAO createUserDAO() {
        return new OracleUserDAO();
    }

    public IRoleDAO createRoleDAO() {
        return new OracleRoleDAO();
    }

    public ICableDAO createCableDAO() {
        return new OracleCableDAO();
    }

    public ICircuitDAO createCircuitDAO() {
        return new OracleCircuitDAO();
    }

    public IDeviceDAO createDeviceDAO() {
        return new OracleDeviceDAO();
    }

    public IPortDAO createPortDAO() {
        return new OraclePortDAO();
    }

    public IPriceDAO createPriceDAO() {
        return new OraclePriceDAO();
    }

    public IProviderLocationDAO createProviderLocationDAO() {
        return new OracleProviderLocationDAO();
    }

    public IServiceDAO createServiceDAO() {
        return new OracleServiceDAO();
    }

    public IServiceInstanceDAO createServiceInstanceDAO() {
        return new OracleServiceInstanceDAO();
    }

    public IServiceLocationDAO createServiceLocationDAO() {
        return new OracleServiceLocationDAO();
    }

    public IServiceOrderDAO createServiceOrderDAO() {
        return new OracleServiceOrderDAO();
    }

    public ITaskDAO createTaskDAO() {
        return new OracleTaskDAO();
    }

}
