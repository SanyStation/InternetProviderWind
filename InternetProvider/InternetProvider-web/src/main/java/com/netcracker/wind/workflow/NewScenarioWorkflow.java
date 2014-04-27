/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.wind.workflow;

import com.netcracker.wind.dao.factory.AbstractFactoryDAO;
import com.netcracker.wind.dao.factory.FactoryCreator;
import com.netcracker.wind.dao.interfaces.ICableDAO;
import com.netcracker.wind.dao.interfaces.IPortDAO;
import com.netcracker.wind.entities.Cable;
import com.netcracker.wind.entities.Port;
import com.netcracker.wind.entities.Service;
import com.netcracker.wind.entities.ServiceLocation;
import com.netcracker.wind.entities.ServiceOrder;
import java.util.List;

/**
 *
 * @author Anatolii
 */
public class NewScenarioWorkflow {

    public static void createTasksForNewScnario(ServiceOrder order) {
        if (!order.getScenario().equals(ServiceOrder.NEW_SCEARIO)) {
            throw new IllegalArgumentException("Service must have 'New' scenario");
        }
        Service service = order.getService();
        ServiceLocation serviceLocation = order.getServiceLocation();
        AbstractFactoryDAO factoryDAO = FactoryCreator.getInstance().getFactory();
        IPortDAO portDAO = factoryDAO.createPortDAO();
        ICableDAO cableDAO = factoryDAO.createCableDAO();
        List<Port> ports = portDAO.findByFree(true);
        List<Cable> cables = cableDAO.findByServiceLocation(serviceLocation.getId());
    }

}
