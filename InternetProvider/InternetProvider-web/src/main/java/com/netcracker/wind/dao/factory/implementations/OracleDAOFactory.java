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
import com.netcracker.wind.dao.impl.TaskDAO;
import com.netcracker.wind.dao.impl.PortDAO;
import com.netcracker.wind.dao.impl.ProviderLocationDAO;
import com.netcracker.wind.dao.impl.CableDAO;
import com.netcracker.wind.dao.impl.ServiceInstanceDAO;
import com.netcracker.wind.dao.impl.ServiceDAO;
import com.netcracker.wind.dao.impl.CircuitDAO;
import com.netcracker.wind.dao.impl.UserDAO;
import com.netcracker.wind.dao.impl.PriceDAO;
import com.netcracker.wind.dao.impl.ServiceLocationDAO;
import com.netcracker.wind.dao.impl.DeviceDAO;
import com.netcracker.wind.dao.impl.RoleDAO;
import com.netcracker.wind.dao.impl.ServiceOrderDAO;
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
