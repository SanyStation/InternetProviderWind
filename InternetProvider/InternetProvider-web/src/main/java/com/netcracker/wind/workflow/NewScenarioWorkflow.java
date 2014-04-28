/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.wind.workflow;

import com.netcracker.wind.dao.factory.AbstractFactoryDAO;
import com.netcracker.wind.dao.factory.FactoryCreator;
import com.netcracker.wind.dao.interfaces.ICableDAO;
import com.netcracker.wind.entities.Cable;
import com.netcracker.wind.entities.Role;
import com.netcracker.wind.entities.ServiceLocation;
import com.netcracker.wind.entities.ServiceOrder;
import com.netcracker.wind.entities.Task;
import java.util.List;

/**
 *
 * @author Anatolii
 */
public class NewScenarioWorkflow {

    public static Task createTaskForNewScnario(ServiceOrder order) {
        if (!order.getScenario().equals(ServiceOrder.NEW_SCEARIO)) {
            throw new IllegalArgumentException("Service must have 'New' scenario");
        }
        ServiceLocation serviceLocation = order.getServiceLocation();
        AbstractFactoryDAO factoryDAO = FactoryCreator.getInstance().getFactory();
        ICableDAO cableDAO = factoryDAO.createCableDAO();
        List<Cable> cables = cableDAO.findByServiceLocation(serviceLocation.getId());
        Task task;
        if (cables.isEmpty()) {
            task = new Task();
            task.setRole(new Role(Role.IE_GROUP_ID));
            task.setServiceOrder(order);
            task.setType(Task.TaskType.NEW_DEVICE.toString());
            task.setStatus(Task.TaskStatus.NEW.toString());
        } else {
            task = createTaskForPE(order);
        }
        return task;
    }

    public static Task createTaskForPE(ServiceOrder order) {
        Task task = new Task();
        task.setRole(new Role(Role.PE_GROUPR_ID));
        task.setServiceOrder(order);
        task.setType(Task.TaskType.CIRCUIT_MANAGMENT.toString());
        task.setStatus(Task.TaskStatus.NEW.toString());
        return task;
    }

    public static Task createTaskForCSE(ServiceOrder order) {
        Task task = new Task();
        task.setRole(new Role(Role.CSE_GROUP_ID));
        task.setServiceOrder(order);
        task.setType(Task.TaskType.SEND_BILL.toString());
        task.setStatus(Task.TaskStatus.NEW.toString());
        return task;
    }
}
