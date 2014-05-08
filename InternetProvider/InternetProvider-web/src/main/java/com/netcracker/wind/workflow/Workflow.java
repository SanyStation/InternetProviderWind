package com.netcracker.wind.workflow;

import com.netcracker.wind.dao.factory.AbstractFactoryDAO;
import com.netcracker.wind.dao.factory.FactoryCreator;
import com.netcracker.wind.dao.implementations.helper.AbstractOracleDAO;
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
            int portId = 0;
            if (port == null) {
                createTaskForIE(order, Task.Type.NEW_DEVICE, taskDAO);
            } else {
                createTaskForIE(order, Task.Type.NEW_CABLE, taskDAO);
                portId = port.getId();
            }
            Circuit circuit = order.getServiceInstance().getCircuit();
            if (circuit == null) {
                circuit = new Circuit();
                circuit.setServiceInstance(order.getServiceInstance());
                circuit.setPortId(portId);
                circuitDAO.add(circuit);
            } else {
                circuit.setPortId(portId);
                circuitDAO.update(circuit);
            }
        } else {
            createTaskForPE(order, Task.Type.CREATE_CIRCUIT, taskDAO);
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
        Task task = TaskCreator.createTask(Role.PE_GROUP_ID, type,
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
                findByTypeAndStatus(AbstractOracleDAO.DEFAULT_PAGE_NUMBER,
                        AbstractOracleDAO.ALL_RECORDS, Task.Type.NEW_DEVICE,
                        Task.Status.NEW, Task.Status.ACTIVE,
                        Task.Status.SUSPENDED);
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
