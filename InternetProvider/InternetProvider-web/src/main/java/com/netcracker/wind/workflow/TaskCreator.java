package com.netcracker.wind.workflow;

import com.netcracker.wind.entities.Role;
import com.netcracker.wind.entities.ServiceOrder;
import com.netcracker.wind.entities.Task;

/**
 *
 * @author Anatolii
 */
public class TaskCreator {

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
