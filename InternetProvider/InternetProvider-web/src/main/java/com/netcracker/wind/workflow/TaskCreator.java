package com.netcracker.wind.workflow;

import com.netcracker.wind.entities.Role;
import com.netcracker.wind.entities.ServiceOrder;
import com.netcracker.wind.entities.Task;

/**
 * Class with static methods for creating {@link Task}
 *
 * @author Anatolii
 */
public class TaskCreator {

    /**
     * Method create instance of class {@link Task}.
     *
     * @param roleId group for new task
     * @param taskType type of new task
     * @param taskStatus status of new task
     * @param order {@link ServiceOrder} for new task
     * @return new task
     * @see Task
     * @see ServiceOrder
     * @see Task.Status
     */
    public static Task createTask(int roleId, Task.Type taskType,
            Task.Status taskStatus, ServiceOrder order) {
        Task task = new Task();
        task.setRole(new Role(roleId));
        task.setType(taskType);
        task.setStatus(taskStatus);
        task.setServiceOrder(order);
        return task;
    }
}
