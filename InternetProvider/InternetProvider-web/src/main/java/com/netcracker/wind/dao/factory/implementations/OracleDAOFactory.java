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
import com.netcracker.wind.dao.implementations.TaskDAO;
import com.netcracker.wind.dao.implementations.PortDAO;
import com.netcracker.wind.dao.implementations.ProviderLocationDAO;
import com.netcracker.wind.dao.implementations.CableDAO;
import com.netcracker.wind.dao.implementations.ServiceInstanceDAO;
import com.netcracker.wind.dao.implementations.ServiceDAO;
import com.netcracker.wind.dao.implementations.CircuitDAO;
import com.netcracker.wind.dao.implementations.UserDAO;
import com.netcracker.wind.dao.implementations.PriceDAO;
import com.netcracker.wind.dao.implementations.ServiceLocationDAO;
import com.netcracker.wind.dao.implementations.DeviceDAO;
import com.netcracker.wind.dao.implementations.RoleDAO;
import com.netcracker.wind.dao.implementations.ServiceOrderDAO;
import com.netcracker.wind.dao.factory.AbstractFactoryDAO;

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
