/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.wind.workflow;

import com.netcracker.wind.dao.factory.AbstractFactoryDAO;
import com.netcracker.wind.dao.factory.FactoryCreator;
import com.netcracker.wind.dao.interfaces.ICableDAO;
import com.netcracker.wind.dao.interfaces.ICircuitDAO;
import com.netcracker.wind.dao.interfaces.IPortDAO;
import com.netcracker.wind.dao.interfaces.ITaskDAO;
import com.netcracker.wind.entities.Cable;
import com.netcracker.wind.entities.Circuit;
import com.netcracker.wind.entities.Port;
import com.netcracker.wind.entities.Role;
import com.netcracker.wind.entities.ServiceOrder;
import com.netcracker.wind.entities.Task;
import java.util.List;

/**
 *
 * @author Anatolii
 */
public class NewScenarioWorkflow {

    public static void createTaskForNewScnario(ServiceOrder order) {
        AbstractFactoryDAO factoryDAO = FactoryCreator.getInstance().getFactory();
        ICableDAO cableDAO = factoryDAO.createCableDAO();
        IPortDAO portDAO = factoryDAO.createPortDAO();
        ITaskDAO taskDAO = factoryDAO.createTaskDAO();
        ICircuitDAO circuitDAO = factoryDAO.createCircuitDAO();

        List<Cable> cables = cableDAO.
                findByServiceLocation(order.getServiceLocation().getId());
        if (cables.isEmpty()) {
            Port port = portDAO.occupyFreePort();
            if (port == null) {
                List<Task> tasks = taskDAO.
                        findByTypeAndStatus(Task.TaskType.NEW_DEVICE.toString(),
                                Task.TaskStatus.NEW.toString(),
                                Task.TaskStatus.ACTIVE.toString(),
                                Task.TaskStatus.SUSPENDED.toString());
                if (tasks.isEmpty()) {
                    Task task = TaskCreator.createTask(Role.IE_GROUP_ID,
                            Task.TaskType.NEW_DEVICE,
                            Task.TaskStatus.NEW,
                            order);
                    taskDAO.add(task);
                }
            } else {

            }
            Circuit circuit = new Circuit();
            circuit.setPort(port);
            circuit.setServiceInstance(order.getServiceInstance());
            circuitDAO.add(circuit);
            Task task = TaskCreator.createTask(Role.IE_GROUP_ID,
                    Task.TaskType.NEW_CABLE,
                    Task.TaskStatus.NEW,
                    order);
            taskDAO.add(task);
        } else {
            createTaskForPE(order, Task.TaskType.MANAGE_CIRCUIT, taskDAO);
        }
    }

    public static void createTaskForPE(ServiceOrder order, Task.TaskType type,
            ITaskDAO taskDAO) {
        Task task = TaskCreator.createTask(Role.PE_GROUPR_ID,
                type,
                Task.TaskStatus.NEW,
                order);
        taskDAO.add(task);
    }

    public static void createTaskForCSE(ServiceOrder order, ITaskDAO taskDAO) {
        Task task = TaskCreator.createTask(Role.CSE_GROUP_ID,
                Task.TaskType.SEND_BILL, Task.TaskStatus.NEW, order);
        taskDAO.add(task);
    }

}
