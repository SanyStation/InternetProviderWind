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
public class Workflow {

    public static void createTaskForNewScnario(ServiceOrder order) {
        AbstractFactoryDAO factoryDAO
                = FactoryCreator.getInstance().getFactory();
        ICableDAO cableDAO = factoryDAO.createCableDAO();
        IPortDAO portDAO = factoryDAO.createPortDAO();
        ITaskDAO taskDAO = factoryDAO.createTaskDAO();
        ICircuitDAO circuitDAO = factoryDAO.createCircuitDAO();

        Cable cable = cableDAO.findByServiceLocation(
                order.getServiceLocation().getId());
        if (cable == null) {
            Port port = portDAO.occupyFreePort();
            if (port == null) {
                createTaskForIE(order, Task.Type.NEW_DEVICE, taskDAO);
            } else {
                createTaskForIE(order, Task.Type.NEW_CABLE, taskDAO);
            }
            Circuit circuit = new Circuit();
            circuit.setPort(port);
            circuit.setServiceInstance(order.getServiceInstance());
            circuitDAO.add(circuit);
        } else {
            createTaskForPE(order, Task.Type.MANAGE_CIRCUIT, taskDAO);
        }
    }

    public static void createTaskForIE(ServiceOrder order,
            Task.Type taskType, ITaskDAO taskDAO) {

        //If in system isn't task to create new device with not completed status
        //then don't create new task NEW_DEVICE
        if (taskType.equals(Task.Type.NEW_DEVICE)
                && isNotComletetdTaskNewDevice(taskDAO)) {
            return;
        }

        Task task = TaskCreator.createTask(Role.IE_GROUP_ID, taskType, 
                Task.Status.NEW, order);
        taskDAO.add(task);
    }

    public static void createTaskForPE(ServiceOrder order, Task.Type type,
            ITaskDAO taskDAO) {
        Task task = TaskCreator.createTask(Role.PE_GROUPR_ID, type,
                Task.Status.NEW, order);
        taskDAO.add(task);
    }

    public static void createTaskForCSE(ServiceOrder order, ITaskDAO taskDAO) {
        Task task = TaskCreator.createTask(Role.CSE_GROUP_ID,
                Task.Type.SEND_BILL, Task.Status.NEW, order);
        taskDAO.add(task);
    }

    private static boolean isNotComletetdTaskNewDevice(ITaskDAO taskDAO) {
        List<Task> tasks = taskDAO.
                findByTypeAndStatus(Task.Type.NEW_DEVICE.toString(),
                        Task.Status.NEW.toString(),
                        Task.Status.ACTIVE.toString(),
                        Task.Status.SUSPENDED.toString());
        return !tasks.isEmpty();
    }

    public static void createTaskForModifyScenario(ServiceOrder order) {
        createTaskForPE(order, Task.Type.MODIFY_CIRCUIT,
                FactoryCreator.getInstance().getFactory().createTaskDAO());
    }

    public static void createTaskForDisconnectScenario(ServiceOrder order) {
        createTaskForPE(order, Task.Type.DELETE_CIRCUIT,
                FactoryCreator.getInstance().getFactory().createTaskDAO());
    }

}
